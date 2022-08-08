package com.rahat.mwltd.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.rahat.mwltd.R;

public class Videoads_Activity extends AppCompatActivity {

    private LinearLayout video1, video2, video3, video4, video5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoads);


        video1=findViewById(R.id.videoadd1);
        video2=findViewById(R.id.videoadd2);
        video3=findViewById(R.id.videoadd3);
        video4=findViewById(R.id.videoadd4);
        video5=findViewById(R.id.videoadd5);

        video1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent( Videoads_Activity.this, Reward_Activity.class);
                startActivity(in);
            }
        });
        video2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent( Videoads_Activity.this,Reward_Activity.class);
                startActivity(in);
            }
        });
        video3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent( Videoads_Activity.this,Reward_Activity.class);
                startActivity(in);
            }
        });
        video4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent( Videoads_Activity.this,Reward_Activity.class);
                startActivity(in);
            }
        });
        video5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent( Videoads_Activity.this,Reward_Activity.class);
                startActivity(in);
            }
        });




    }
}