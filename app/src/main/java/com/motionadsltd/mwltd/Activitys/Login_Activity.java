package com.motionadsltd.mwltd.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.motionadsltd.mwltd.R;
import com.motionadsltd.mwltd.databinding.ActivityLoginBinding;


public class Login_Activity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog = new ProgressDialog(Login_Activity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading....");
        mAuth = FirebaseAuth.getInstance();
        //check user is login or not
        binding.loginbtn.setOnClickListener(v -> {
            String mail, pass;
            mail = binding.lmail.getText().toString();
            pass = binding.lpass.getText().toString();
            //checking data validation
            if (mail.isEmpty()) {
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (pass.isEmpty()) {
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                return;
            }
            progressDialog.show();
            loginUser(mail, pass);

        });
        binding.newregister.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Register_Activity.class));
        });

    }

    private void loginUser(String mail, String pass) {
        //checking user data for login
        mAuth.signInWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finishAffinity();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(Login_Activity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}