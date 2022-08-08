package com.rahat.mwltd.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.rahat.mwltd.databinding.ActivityRegisterBinding;

import java.util.HashMap;

public class Register_Activity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }


    private void CreateAccount(final String username, final String password) {


    }



    private void updateUi (final String username, final String password){

        HashMap<String,Object> map = new HashMap<>();
        map.put("user",username);
        map.put("pass",password);
        map.put("mobile","01868421625");

        //ডাটা সেইভ বা ডাটাবেইজ এ আপলোড করুন



        ;}

    }
