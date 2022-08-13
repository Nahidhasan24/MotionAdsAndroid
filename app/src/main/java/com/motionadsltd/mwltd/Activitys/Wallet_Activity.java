package com.motionadsltd.mwltd.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.motionadsltd.mwltd.Models.Appconfig;
import com.motionadsltd.mwltd.Models.UserModels;
import com.motionadsltd.mwltd.R;
import com.motionadsltd.mwltd.databinding.ActivityWalletBinding;

public class Wallet_Activity extends AppCompatActivity {


    ActivityWalletBinding binding;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    DatabaseReference mConfig;
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
        getUserData();
        binding.withdrowbtn.setOnClickListener(view -> {
           binding.group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(RadioGroup radioGroup, int i) {
                   mathod=getId(i);
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
}