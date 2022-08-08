package com.motionadsltd.mwltd.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.motionadsltd.mwltd.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("users");
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading....");
        binding.videoAdsBtn.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), Videoads_Activity.class));
            ///
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        });

        ///
        binding.walletbtn.setOnClickListener(view -> {
            Intent in=new Intent( MainActivity.this, Wallet_Activity.class);
            startActivity(in);
        });

        ///
        binding.aboutusbtn.setOnClickListener(view -> {

            Intent in=new Intent( MainActivity.this, About_Activity.class);
            startActivity(in);
        });
        ///
        binding.noticebtn.setOnClickListener(view -> {

            Intent in=new Intent( MainActivity.this, Notice_Activity.class);
            startActivity(in);
        });
        ///
        binding.helpcenterbtn.setOnClickListener(view -> {

            Intent in=new Intent( MainActivity.this, HelpCenter_Activity.class);
            startActivity(in);
        });

        ///
        binding.viewadsbtn.setOnClickListener(view -> {

            Intent in=new Intent( MainActivity.this, Clickads_Activity.class);
            startActivity(in);
        });
        ///
        binding.visitadsbtn.setOnClickListener(view -> {

            Intent in=new Intent( MainActivity.this, Webvisit_Activity.class);
            startActivity(in);
        });

        ///
        binding.addmoney.setOnClickListener(view -> {

            Intent in=new Intent( MainActivity.this, AddMoneyActivity.class);
            startActivity(in);
        });





    }
}