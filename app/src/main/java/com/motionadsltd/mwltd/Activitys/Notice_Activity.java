package com.motionadsltd.mwltd.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebViewClient;

import com.motionadsltd.mwltd.R;
import com.motionadsltd.mwltd.databinding.ActivityNoticeBinding;

public class Notice_Activity extends AppCompatActivity {

    ActivityNoticeBinding binding;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityNoticeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        url=getIntent().getStringExtra("url");
        binding.noticeview.setWebViewClient(new WebViewClient());
        binding.noticeview.setWebViewClient(new WebViewClient());
        binding.noticeview.getSettings().setJavaScriptEnabled(true);
        binding.noticeview.loadData(url,"text/html", "UTF-8");
    }
}