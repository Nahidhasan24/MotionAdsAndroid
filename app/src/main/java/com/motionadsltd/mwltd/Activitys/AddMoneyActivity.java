package com.motionadsltd.mwltd.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.motionadsltd.mwltd.Models.AddModle;
import com.motionadsltd.mwltd.Models.Appconfig;
import com.motionadsltd.mwltd.R;
import com.motionadsltd.mwltd.databinding.ActivityAddMoneyBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddMoneyActivity extends AppCompatActivity {

    ActivityAddMoneyBinding binding;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    DatabaseReference mAddMoney;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddMoneyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("appconfig");
        mAddMoney = FirebaseDatabase.getInstance().getReference().child("addmoney");
        progressDialog = new ProgressDialog(AddMoneyActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading....");
        progressDialog.show();
        getData();
        checkData();
        binding.submitBtn.setOnClickListener(v->{
            String amoun,tranid;
            amoun=binding.amount.getText().toString();
            tranid=binding.tranID.getText().toString();
            if (amoun.isEmpty()){
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                return;
            }if (tranid.isEmpty()){
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                return;
            }
            uploadData(amoun,tranid);
            progressDialog.show();
        });
    }

    private void uploadData(String amoun, String tranid) {
        AddModle addModle=new AddModle(mAuth.getUid(),tranid,getTimeDate(),Integer.parseInt(amoun.replace(",","")));
        mAddMoney.child(mAuth.getUid())
                .setValue(addModle)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        binding.amount.setText("");
                        binding.tranID.setText("");
                        if(task.isSuccessful()){
                            Toast.makeText(AddMoneyActivity.this, "Successfully sent to server", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AddMoneyActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void checkData() {
        mAddMoney.child(mAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDialog.dismiss();
                if (snapshot.exists()){
                    binding.submitBtn.setEnabled(false);
                    Toast.makeText(AddMoneyActivity.this, "your one request on list", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
            }
        });
    }

    private void getData() {
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Appconfig appconfig=snapshot.getValue(Appconfig.class);
                    binding.noticeTV.setText(""+appconfig.getAddmoneynotice());
                    binding.numberTV.setText(""+appconfig.getAddmoneynumber());
                }
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