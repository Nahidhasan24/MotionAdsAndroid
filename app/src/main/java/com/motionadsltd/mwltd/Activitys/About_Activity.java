package com.motionadsltd.mwltd.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.motionadsltd.mwltd.R;

public class About_Activity extends AppCompatActivity {

     WebView mwebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mwebsite = findViewById(R.id.mwebsite);
        mwebsite.getSettings().setJavaScriptEnabled(true);
        mwebsite.setWebViewClient(new WebViewClient());
        mwebsite.setWebChromeClient(new WebChromeClient());
        mwebsite.loadUrl("https://motionadsltd.com/");


    }
}