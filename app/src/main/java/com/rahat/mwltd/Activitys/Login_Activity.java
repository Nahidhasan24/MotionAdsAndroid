package com.rahat.mwltd.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rahat.mwltd.R;


public class Login_Activity<LoginActivity> extends AppCompatActivity {

    private EditText username,pass;
    private Button login;
    private TextView newregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        username = findViewById(R.id.lusername);
        pass = findViewById(R.id.lpass);
        login = findViewById(R.id.loginbtn);
        newregister = findViewById(R.id.newregister);


        newregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Login_Activity.this, Register_Activity.class);
                startActivity(in);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               startActivity(new Intent(getApplicationContext(), MainActivity.class));


            }
        });
    }



}