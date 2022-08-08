package com.motionadsltd.mwltd.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.motionadsltd.mwltd.R;
import com.motionadsltd.mwltd.databinding.ActivityLoginBinding;


public class Login_Activity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());


    }



}