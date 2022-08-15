package com.motionadsltd.mwltd.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.motionadsltd.mwltd.Models.UserModels;
import com.motionadsltd.mwltd.databinding.ActivityRegisterBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Register_Activity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("users");
        progressDialog = new ProgressDialog(Register_Activity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading....");
        binding.registerBtn.setOnClickListener(v -> {
            //getting data from ui
            String name, mail, phone, pass, cpass;
            name = binding.rname.getText().toString();
            mail = binding.rmail.getText().toString();
            phone = binding.rmobile.getText().toString();
            pass = binding.rpass1.getText().toString();
            cpass = binding.rpass2.getText().toString();

            //checking data validation here
            if (name.isEmpty()) {
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (mail.isEmpty()) {
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!mail.contains("@")) {
                Toast.makeText(this, "Invalid Mail", Toast.LENGTH_SHORT).show();
                return;
            }
            if (phone.isEmpty()) {
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (phone.length() < 11) {
                Toast.makeText(this, "invalid Number", Toast.LENGTH_SHORT).show();
                return;
            }
            if (phone.length() > 11) {
                Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show();
                return;
            }
            if (pass.isEmpty()) {
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (cpass.isEmpty()) {
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!pass.equals(cpass)) {
                Toast.makeText(this, "Password Not Same", Toast.LENGTH_SHORT).show();
                return;
            }
            //all data is correct
            createAccount(name, mail, phone, pass);
            progressDialog.show();
        });
        binding.alreadyregis.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Register_Activity.class));
        });

    }


    private void createAccount(String name, String mail, String phone, String pass) {

        //creating user account on firebase auth
        mAuth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //putting data on firebase realtime database
                            UserModels userModels = new UserModels(name, mail, phone, mAuth.getUid(), "on", getTimeDate(), "free", getReferCode(), "", getTimeDate(),"", 0, 0);
                            mRef.child(mAuth.getUid())
                                    .setValue(userModels)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                progressDialog.dismiss();
                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                finishAffinity();
                                            } else {
                                                progressDialog.dismiss();
                                                Toast.makeText(Register_Activity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                                                Log.d("MyTag", "onComplete: " + task.getException());
                                            }
                                        }
                                    });

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(Register_Activity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                            Log.d("MyTag", "onComplete: " + task.getException());
                        }
                    }
                });

    }

    //getting current time
    private String getTimeDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    //generating new and unique refer code for each users
    private String getReferCode() {
        Random rand = new Random();
        int refer = rand.nextInt(9999) + 99191;
        return String.valueOf("MW" + refer + "LTD");
    }


}
