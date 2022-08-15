package com.motionadsltd.mwltd.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.motionadsltd.mwltd.Models.UserModels;
import com.motionadsltd.mwltd.Models.VideoAdsModel;
import com.motionadsltd.mwltd.R;
import com.motionadsltd.mwltd.databinding.ActivityVideoadsBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Videoads_Activity extends AppCompatActivity {

    ActivityVideoadsBinding binding;
    DatabaseReference mRef;
    DatabaseReference mUser;
    FirebaseAuth mAuth;
    VideoAdsModel videoAdsModel;
    UserModels userModels;
    ProgressDialog progressDialog;
    int GET_USER_COIN=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityVideoadsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog=new ProgressDialog(Videoads_Activity.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        mAuth=FirebaseAuth.getInstance();
        mRef= FirebaseDatabase.getInstance().getReference().child("ads");
        mUser= FirebaseDatabase.getInstance().getReference().child("users");
        progressDialog.show();
        GET_USER_COIN=getIntent().getIntExtra("coin",0);
        checkButtons();
        getUserData();
        binding.videoadd1.setOnClickListener(v->{

            HashMap<String,Object> map=new HashMap<>();
            map.put("ad1","done");
            mRef.child(mAuth.getUid())
                    .child("videoads")
                    .updateChildren(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Videoads_Activity.this, "Done", Toast.LENGTH_SHORT).show();
                                if (videoAdsModel.getAd1().equals("done")&&
                                        videoAdsModel.getAd2().equals("done")&&
                                        videoAdsModel.getAd3().equals("done")&&
                                        videoAdsModel.getAd4().equals("done")&&
                                        videoAdsModel.getAd5().equals("done")){

                                    int use=videoAdsModel.getUse()+1;
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("use",use);
                                    map.put("last",getIncreasTime());
                                    mRef.child(mAuth.getUid())
                                            .child("videoads")
                                            .updateChildren(map);

                                }
                            }else{
                                Toast.makeText(Videoads_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
        binding.videoadd2.setOnClickListener(v->{

            HashMap<String,Object> map=new HashMap<>();
            map.put("ad2","done");
            mRef.child(mAuth.getUid())
                    .child("videoads")
                    .updateChildren(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Videoads_Activity.this, "Done", Toast.LENGTH_SHORT).show();
                                if (videoAdsModel.getAd1().equals("done")&&
                                        videoAdsModel.getAd2().equals("done")&&
                                        videoAdsModel.getAd3().equals("done")&&
                                        videoAdsModel.getAd4().equals("done")&&
                                        videoAdsModel.getAd5().equals("done")){
                                    int use=videoAdsModel.getUse()+1;
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("use",use);
                                    map.put("last",getIncreasTime());
                                    mRef.child(mAuth.getUid())
                                            .child("videoads")
                                            .updateChildren(map);

                                }
                            }else{
                                Toast.makeText(Videoads_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
        binding.videoadd3.setOnClickListener(v->{

            HashMap<String,Object> map=new HashMap<>();
            map.put("ad3","done");
            mRef.child(mAuth.getUid())
                    .child("videoads")
                    .updateChildren(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Videoads_Activity.this, "Done", Toast.LENGTH_SHORT).show();
                                if (videoAdsModel.getAd1().equals("done")&&
                                        videoAdsModel.getAd2().equals("done")&&
                                        videoAdsModel.getAd3().equals("done")&&
                                        videoAdsModel.getAd4().equals("done")&&
                                        videoAdsModel.getAd5().equals("done")){
                                    int use=videoAdsModel.getUse()+1;
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("use",use);
                                    map.put("last",getIncreasTime());
                                    mRef.child(mAuth.getUid())
                                            .child("videoads")
                                            .updateChildren(map);

                                }
                            }else{
                                Toast.makeText(Videoads_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
        binding.videoadd4.setOnClickListener(v->{

            HashMap<String,Object> map=new HashMap<>();
            map.put("ad4","done");
            mRef.child(mAuth.getUid())
                    .child("videoads")
                    .updateChildren(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Videoads_Activity.this, "Done", Toast.LENGTH_SHORT).show();
                                if (videoAdsModel.getAd1().equals("done")&&
                                        videoAdsModel.getAd2().equals("done")&&
                                        videoAdsModel.getAd3().equals("done")&&
                                        videoAdsModel.getAd4().equals("done")&&
                                        videoAdsModel.getAd5().equals("done")){
                                    int use=videoAdsModel.getUse()+1;
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("use",use);
                                    map.put("last",getIncreasTime());
                                    mRef.child(mAuth.getUid())
                                            .child("videoads")
                                            .updateChildren(map);

                                }
                            }else{
                                Toast.makeText(Videoads_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
        binding.videoadd5.setOnClickListener(v->{

            HashMap<String,Object> map=new HashMap<>();
            map.put("ad5","done");
            mRef.child(mAuth.getUid())
                    .child("videoads")
                    .updateChildren(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Videoads_Activity.this, "Done", Toast.LENGTH_SHORT).show();
                                if (videoAdsModel.getAd1().equals("done")&&
                                        videoAdsModel.getAd2().equals("done")&&
                                        videoAdsModel.getAd3().equals("done")&&
                                        videoAdsModel.getAd4().equals("done")&&
                                        videoAdsModel.getAd5().equals("done")){
                                    int use=videoAdsModel.getUse()+1;
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("use",use);
                                    map.put("last",getIncreasTime());
                                    mRef.child(mAuth.getUid())
                                            .child("videoads")
                                            .updateChildren(map)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){

                                                int mainCoin=userModels.getCoin()+GET_USER_COIN;
                                                HashMap<String,Object> hashMap=new HashMap<>();
                                                hashMap.put("coin",mainCoin);
                                                mUser.child(mAuth.getUid())
                                                        .updateChildren(hashMap);

                                            }else{
                                                Toast.makeText(Videoads_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                }
                            }else{
                                Toast.makeText(Videoads_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }

    private void checkButtons() {
        mRef.child(mAuth.getUid())
                .child("videoads")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        progressDialog.dismiss();
                        if (snapshot.exists()){
                             videoAdsModel=snapshot.getValue(VideoAdsModel.class);
                            if (videoAdsModel.getAd1().equals("done")){
                                binding.videoadd1.setVisibility(View.GONE);
                            }

                            if (videoAdsModel.getAd2().equals("done")){
                                binding.videoadd2.setVisibility(View.GONE);
                            }

                            if (videoAdsModel.getAd3().equals("done")){
                                binding.videoadd3.setVisibility(View.GONE);
                            }

                            if (videoAdsModel.getAd4().equals("done")){
                                binding.videoadd4.setVisibility(View.GONE);
                            }

                            if (videoAdsModel.getAd5().equals("done")){
                                binding.videoadd5.setVisibility(View.GONE);
                            }


                            if (videoAdsModel.getUse()>=4){
                                HashMap<String,Object> map=new HashMap<>();
                                map.put("time",getTimeDate());
                                mRef.child(mAuth.getUid())
                                        .child("videoads")
                                        .updateChildren(map)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    onBackPressed();
                                                }else{
                                                    Toast.makeText(Videoads_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                                    onBackPressed();
                                                }
                                            }
                                        });
                            }


                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
    private String getIncreasTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String currentDateandTime = sdf.format(new Date());

        Date date = null;
        try {
            date = sdf.parse(currentDateandTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 1);

        return String.valueOf(calendar.getTime().getHours());
    }
    private String getTimeDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }
    private void getUserData() {
        mUser.child(mAuth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        userModels=snapshot.getValue(UserModels.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}