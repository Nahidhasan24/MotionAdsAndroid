package com.motionadsltd.mwltd.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.motionadsltd.mwltd.R;

public class Webvisit_Activity extends AppCompatActivity {

    private LinearLayout Weba, Webb,Webc, Webd,Webe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webvisit);

       Weba=findViewById(R.id.web1);
        Webb=findViewById(R.id.web2);
        Webc=findViewById(R.id.web3);
        Webd=findViewById(R.id.web4);
        Webe=findViewById(R.id.web5);



        Weba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent( Webvisit_Activity.this, BannerAds.class);
                startActivity(in);
            }
        });

        Webb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent( Webvisit_Activity.this,BannerAds.class);
                startActivity(in);
            }
        });

        Webc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent( Webvisit_Activity.this,BannerAds.class);
                startActivity(in);
            }
        });

        Webd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent( Webvisit_Activity.this,BannerAds.class);
                startActivity(in);
            }
        });

        Webe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent( Webvisit_Activity.this,BannerAds.class);
                startActivity(in);
            }
        });


    }
}