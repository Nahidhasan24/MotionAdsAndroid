package com.motionadsltd.mwltd.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.motionadsltd.mwltd.Models.Appconfig;
import com.motionadsltd.mwltd.Models.UserModels;
import com.motionadsltd.mwltd.Models.WithdrawModle;
import com.motionadsltd.mwltd.R;
import com.motionadsltd.mwltd.databinding.ActivityWalletBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Wallet_Activity extends AppCompatActivity {


    ActivityWalletBinding binding;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    DatabaseReference mWithdraw;
    DatabaseReference mConfig;
    DatabaseReference mHistory;
    UserModels userModels;
    Appconfig appconfig;
    String mathod,number,amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWalletBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("users");
        mConfig = FirebaseDatabase.getInstance().getReference().child("appconfig");
        mWithdraw = FirebaseDatabase.getInstance().getReference().child("withdraw");
        mHistory = FirebaseDatabase.getInstance().getReference().child("history");
        getUserData();
        binding.withdrowbtn.setOnClickListener(view -> {
           binding.group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(RadioGroup radioGroup, int i) {
                   mathod=getId(i);
               }
           });
           number=binding.number.getText().toString();
           amount=binding.amount.getText().toString();
           int am=Integer.parseInt(amount);
           if (number.isEmpty()){
               Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
               return;
           }if (amount.isEmpty()){
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                return;
            }if (am<=appconfig.getMinwithdraw()){
                Toast.makeText(this, "You need minimum "+appconfig.getMinwithdraw()+" coin to withdraw", Toast.LENGTH_LONG).show();
                return;
            }
           int mainCoin=userModels.getCoin()-am;


            WithdrawModle withdrawModle=new WithdrawModle(userModels.getName(),mAuth.getUid(),number,getTimeDate(),mathod,"pending",am);
            HashMap<String,Object> map=new HashMap<>();
            map.put("coin",mainCoin);
            mRef.child(mAuth.getUid())
                    .updateChildren(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                mWithdraw.child(mAuth.getUid())
                                        .setValue(withdrawModle)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){

                                                    mHistory.child(mAuth.getUid())
                                                            .push()
                                                            .setValue(withdrawModle)
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()){
                                                                        Toast.makeText(Wallet_Activity.this, "Your Request Successfully submitted ", Toast.LENGTH_SHORT).show();
                                                                    }else{
                                                                        Toast.makeText(Wallet_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }
                                                            });
                                                    binding.withdrowbtn.setEnabled(false);
                                                }else{
                                                    Toast.makeText(Wallet_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }else{
                                Toast.makeText(Wallet_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



       });

    }
    private String getId(int id){
        String name=null;
        switch (id){
            case R.id.bkash:
                name="Bkash";
                break;
            case R.id.nagad:
                name="Nagad";
                break;
            case R.id.upay:
                name="Upay";
                break;
        }

        return name;
    }

    private void getUserData() {
        mWithdraw.child(mAuth.getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    binding.withdrowbtn.setEnabled(false);

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
        mRef.child(mAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        userModels=snapshot.getValue(UserModels.class);
                        binding.balenceTV.setText(""+userModels.getCoin()+" coin");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        mConfig.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                appconfig=snapshot.getValue(Appconfig.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private String getTimeDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }
}