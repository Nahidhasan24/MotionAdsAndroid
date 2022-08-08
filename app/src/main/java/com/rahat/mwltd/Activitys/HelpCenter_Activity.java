package com.rahat.mwltd.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.rahat.mwltd.R;

public class HelpCenter_Activity extends AppCompatActivity {

    ImageView whatsapp, youtube,facebook,facebookgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);


        whatsapp=findViewById(R.id.whatsapph);
        youtube=findViewById(R.id.yiutubeh);
        facebook=findViewById(R.id.facebookidmd);
        facebookgr=findViewById(R.id.facebookgrp);


        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://chat.whatsapp.com/Fg8WV7Ejc89E2GUAonwHA9");
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/channel/UCT0uArUSV8e_BemOr7qh3Lg");
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.facebook.com/RahatJidniofficial");
            }
        });

        facebookgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.facebook.com/groups/1094229474538911");
            }
        });

    }

    private void gotoUrl(String s) {

        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}