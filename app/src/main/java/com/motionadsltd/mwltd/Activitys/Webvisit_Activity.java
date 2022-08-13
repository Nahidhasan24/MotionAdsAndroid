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
import com.motionadsltd.mwltd.Models.InterAds;
import com.motionadsltd.mwltd.Models.UserModels;
import com.motionadsltd.mwltd.Models.VisiteAdsModle;
import com.motionadsltd.mwltd.R;
import com.motionadsltd.mwltd.databinding.ActivityWebvisitBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Webvisit_Activity extends AppCompatActivity {


    ActivityWebvisitBinding binding;
    DatabaseReference mRef;
    DatabaseReference mUser;
    FirebaseAuth mAuth;
    VisiteAdsModle interAds;
    UserModels userModels;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWebvisitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog=new ProgressDialog(Webvisit_Activity.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        mAuth= FirebaseAuth.getInstance();
        mRef= FirebaseDatabase.getInstance().getReference().child("ads");
        mUser= FirebaseDatabase.getInstance().getReference().child("users");
        progressDialog.show();
        checkButtons();
        getUserData();
        binding.web1.setOnClickListener(v->{

            HashMap<String,Object> map=new HashMap<>();
            map.put("ad1","done");
            mRef.child(mAuth.getUid())
                    .child("clickads")
                    .updateChildren(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Webvisit_Activity.this, "Done", Toast.LENGTH_SHORT).show();
                                if (interAds.getAd1().equals("done")&&
                                        interAds.getAd2().equals("done")&&
                                        interAds.getAd3().equals("done")&&
                                        interAds.getAd4().equals("done")&&
                                        interAds.getAd5().equals("done")){

                                    int use=interAds.getUse()+1;
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("use",use);
                                    map.put("last",getIncreasTime());
                                    mRef.child(mAuth.getUid())
                                            .child("clickads")
                                            .updateChildren(map);

                                }
                            }else{
                                Toast.makeText(Webvisit_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
        binding.web2.setOnClickListener(v->{

            HashMap<String,Object> map=new HashMap<>();
            map.put("ad2","done");
            mRef.child(mAuth.getUid())
                    .child("clickads")
                    .updateChildren(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Webvisit_Activity.this, "Done", Toast.LENGTH_SHORT).show();
                                if (interAds.getAd1().equals("done")&&
                                        interAds.getAd2().equals("done")&&
                                        interAds.getAd3().equals("done")&&
                                        interAds.getAd4().equals("done")&&
                                        interAds.getAd5().equals("done")){

                                    int use=interAds.getUse()+1;
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("use",use);
                                    map.put("last",getIncreasTime());
                                    mRef.child(mAuth.getUid())
                                            .child("clickads")
                                            .updateChildren(map);

                                }
                            }else{
                                Toast.makeText(Webvisit_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
        binding.web3.setOnClickListener(v->{

            HashMap<String,Object> map=new HashMap<>();
            map.put("ad3","done");
            mRef.child(mAuth.getUid())
                    .child("clickads")
                    .updateChildren(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Webvisit_Activity.this, "Done", Toast.LENGTH_SHORT).show();
                                if (interAds.getAd1().equals("done")&&
                                        interAds.getAd2().equals("done")&&
                                        interAds.getAd3().equals("done")&&
                                        interAds.getAd4().equals("done")&&
                                        interAds.getAd5().equals("done")){

                                    int use=interAds.getUse()+1;
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("use",use);
                                    map.put("last",getIncreasTime());
                                    mRef.child(mAuth.getUid())
                                            .child("clickads")
                                            .updateChildren(map);

                                }
                            }else{
                                Toast.makeText(Webvisit_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
        binding.web4.setOnClickListener(v->{

            HashMap<String,Object> map=new HashMap<>();
            map.put("ad4","done");
            mRef.child(mAuth.getUid())
                    .child("clickads")
                    .updateChildren(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Webvisit_Activity.this, "Done", Toast.LENGTH_SHORT).show();
                                if (interAds.getAd1().equals("done")&&
                                        interAds.getAd2().equals("done")&&
                                        interAds.getAd3().equals("done")&&
                                        interAds.getAd4().equals("done")&&
                                        interAds.getAd5().equals("done")){

                                    int use=interAds.getUse()+1;
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("use",use);
                                    map.put("last",getIncreasTime());
                                    mRef.child(mAuth.getUid())
                                            .child("clickads")
                                            .updateChildren(map);

                                }
                            }else{
                                Toast.makeText(Webvisit_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
        binding.web5.setOnClickListener(v->{
            //check ads click//
            updateData();
        });



    }

    private void updateData() {
        HashMap<String,Object> map=new HashMap<>();
        map.put("ad5","done");
        mRef.child(mAuth.getUid())
                .child("clickads")
                .updateChildren(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Webvisit_Activity.this, "Done", Toast.LENGTH_SHORT).show();
                            if (interAds.getAd1().equals("done")&&
                                    interAds.getAd2().equals("done")&&
                                    interAds.getAd3().equals("done")&&
                                    interAds.getAd4().equals("done")&&
                                    interAds.getAd5().equals("done")){

                                int use=interAds.getUse()+1;
                                HashMap<String, Object> map = new HashMap<>();
                                map.put("use",use);
                                map.put("last",getIncreasTime());
                                mRef.child(mAuth.getUid())
                                        .child("clickads")
                                        .updateChildren(map)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){

                                                    int mainCoin=userModels.getCoin()+1;
                                                    HashMap<String,Object> hashMap=new HashMap<>();
                                                    hashMap.put("coin",mainCoin);
                                                    mUser.child(mAuth.getUid())
                                                            .updateChildren(hashMap);

                                                }else{
                                                    Toast.makeText(Webvisit_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                            }
                        }else{
                            Toast.makeText(Webvisit_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

    private void checkButtons() {
        mRef.child(mAuth.getUid())
                .child("clickads")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        progressDialog.dismiss();
                        if (snapshot.exists()){
                            interAds=snapshot.getValue(VisiteAdsModle.class);



                            if (interAds.getAd1().equals("done")){
                                binding.web1.setVisibility(View.GONE);
                            }

                            if (interAds.getAd2().equals("done")){
                                binding.web2.setVisibility(View.GONE);
                            }

                            if (interAds.getAd3().equals("done")){
                                binding.web3.setVisibility(View.GONE);
                            }

                            if (interAds.getAd4().equals("done")){
                                binding.web4.setVisibility(View.GONE);
                            }

                            if (interAds.getAd5().equals("done")){
                                binding.web5.setVisibility(View.GONE);
                            }


                            if (interAds.getUse()>=4){
                                HashMap<String,Object> map=new HashMap<>();
                                map.put("time",getTimeDate());
                                mRef.child(mAuth.getUid())
                                        .child("clickads")
                                        .updateChildren(map)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    onBackPressed();
                                                }else{
                                                    Toast.makeText(Webvisit_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
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
}