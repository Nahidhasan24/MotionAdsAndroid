package com.motionadsltd.mwltd.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.motionadsltd.mwltd.Adapters.SliderAdapterExample;
import com.motionadsltd.mwltd.Models.Appconfig;
import com.motionadsltd.mwltd.Models.InterAds;
import com.motionadsltd.mwltd.Models.PlanModle;
import com.motionadsltd.mwltd.Models.SliderItem;
import com.motionadsltd.mwltd.Models.UserModels;
import com.motionadsltd.mwltd.Models.VideoAdsModel;
import com.motionadsltd.mwltd.Models.VisiteAdsModle;
import com.motionadsltd.mwltd.databinding.ActivityMainBinding;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    DatabaseReference mSlider;
    DatabaseReference mAds;
    DatabaseReference mPlan;
    PlanModle planModle;
    VideoAdsModel videoAdsModel;
    UserModels userModels;
    int USER_COIN=0;
    DatabaseReference mConfig;
    Appconfig appconfig;
    ProgressDialog progressDialog;
    SliderAdapterExample sliderAdapterExample;
    ArrayList<SliderItem> sliderItemArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("users");
        mSlider = FirebaseDatabase.getInstance().getReference().child("slider");
        mConfig = FirebaseDatabase.getInstance().getReference().child("appconfig");
        mAds = FirebaseDatabase.getInstance().getReference().child("ads");
        mPlan = FirebaseDatabase.getInstance().getReference().child("plan");
        // init progress dialog
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading....");
        progressDialog.show();
        loadSlider();
        getAppcofig();
        getVideoAdsData();
        getInterAdsData();
        getClickAdsData();
        getAccountStat();

        binding.videoAdsBtn.setOnClickListener(v -> {

            mAds.child(mAuth.getUid())
                    .child("videoads")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (!snapshot.exists()) {
                                VideoAdsModel videoAdsModel = new VideoAdsModel("no", "no", "no", "no", "no", mAuth.getUid(), getTimeDate(), "", 0);
                                mAds.child(mAuth.getUid())
                                        .child("videoads")
                                        .setValue(videoAdsModel)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    startActivity(new Intent(getApplicationContext(), Videoads_Activity.class)
                                                            .putExtra("coin",USER_COIN)
                                                    );
                                                } else {
                                                    Toast.makeText(MainActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                startActivity(new Intent(getApplicationContext(), Videoads_Activity.class));
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
            ///

        });
        ///
        binding.walletbtn.setOnClickListener(view -> {
            Intent in = new Intent(MainActivity.this, Wallet_Activity.class);
            startActivity(in);
        });
        ///
        binding.aboutusbtn.setOnClickListener(view -> {

            Intent in = new Intent(MainActivity.this, About_Activity.class);
            startActivity(in);
        });
        ///
        binding.noticebtn.setOnClickListener(view -> {

            Intent in = new Intent(MainActivity.this, Notice_Activity.class);
            in.putExtra("url", appconfig.getNotice());
            startActivity(in);
        });
        ///
        binding.helpcenterbtn.setOnClickListener(view -> {

            Intent in = new Intent(MainActivity.this, HelpCenter_Activity.class);
            startActivity(in);
        });
        ///
        binding.viewadsbtn.setOnClickListener(view -> {

            mAds.child(mAuth.getUid())
                    .child("interads")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (!snapshot.exists()){
                                InterAds interAds=new InterAds("no","no","no","no","no",mAuth.getUid(),getTimeDate(),"",0);
                                mAds.child(mAuth.getUid())
                                        .child("interads")
                                        .setValue(interAds)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Intent in = new Intent(MainActivity.this, Clickads_Activity.class);
                                                    in.putExtra("coin",USER_COIN);
                                                    startActivity(in);
                                                }else{
                                                    Toast.makeText(MainActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }else{
                                Intent in = new Intent(MainActivity.this, Clickads_Activity.class);
                                startActivity(in);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


        });
        ///
        binding.visitadsbtn.setOnClickListener(view -> {
            mAds.child(mAuth.getUid())
                    .child("clickads")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (!snapshot.exists()){
                                VisiteAdsModle visiteAdsModle=new VisiteAdsModle("no","no","no","no","no",mAuth.getUid(),getTimeDate(),"",0,0);
                                mAds.child(mAuth.getUid())
                                        .child("clickads")
                                        .setValue(visiteAdsModle)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Intent in = new Intent(MainActivity.this, Webvisit_Activity.class);
                                                    in.putExtra("coin",USER_COIN);
                                                    startActivity(in);
                                                }else{
                                                    Toast.makeText(MainActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }else {
                                Intent in = new Intent(MainActivity.this, Webvisit_Activity.class);
                                startActivity(in);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


        });
        ///
        binding.addmoney.setOnClickListener(view -> {

            Intent in = new Intent(MainActivity.this, AddMoneyActivity.class);
            startActivity(in);
        });
        ///
        binding.profileBtn.setOnClickListener(c -> {
            startActivity(new Intent(getApplicationContext(), Profile_Activity.class));
        });


    }

    private void getAccountStat() {
        if (userModels.getStatus().equals("off")){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Alert !");
            builder.setMessage("Your Account is Band !");
            builder.setCancelable(false);
            builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    private void getVideoAdsData() {
        mAds.child(mAuth.getUid())
                .child("videoads")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            videoAdsModel = snapshot.getValue(VideoAdsModel.class);


                            if (getTimeDate().compareTo(videoAdsModel.getTime()) < 1) {
                                /// not meet 24 hour.//
                            } else {
                                mAds.child(mAuth.getUid())
                                        .child("videoads")
                                        .removeValue();
                            }



                            if (videoAdsModel.getAd1().equals("done") &&
                                    videoAdsModel.getAd2().equals("done") &&
                                    videoAdsModel.getAd3().equals("done") &&
                                    videoAdsModel.getAd4().equals("done") &&
                                    videoAdsModel.getAd5().equals("done")) {

                                DateFormat dateFormat = new SimpleDateFormat("HH", Locale.US);
                                try {
                                    Date date1 = dateFormat.parse(videoAdsModel.getLast());
                                    Date date2 = dateFormat.parse(getCurrentDate());
                                    if (date1.compareTo(date2) > 0) {
                                        Log.i("app", "Date1 is after Date2");
                                        Toast.makeText(MainActivity.this, "Not finished yet", Toast.LENGTH_SHORT).show();

                                    } else if (date1.compareTo(date2) <= 0) {

                                        if (videoAdsModel.getAd1().equals("no") &&
                                                videoAdsModel.getAd2().equals("no") &&
                                                videoAdsModel.getAd3().equals("no") &&
                                                videoAdsModel.getAd4().equals("no") &&
                                                videoAdsModel.getAd5().equals("no")) {

                                        } else {
                                            HashMap<String, Object> map = new HashMap<>();
                                            map.put("ad1", "no");
                                            map.put("ad2", "no");
                                            map.put("ad3", "no");
                                            map.put("ad4", "no");
                                            map.put("ad5", "no");
                                            map.put("last", "");

                                            mAds.child(mAuth.getUid())
                                                    .child("videoads")
                                                    .updateChildren(map);

                                        }
                                    }


                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
    private void getInterAdsData() {
        mAds.child(mAuth.getUid())
                .child("interads")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            videoAdsModel = snapshot.getValue(VideoAdsModel.class);


                            if (getTimeDate().compareTo(videoAdsModel.getTime()) < 1) {
                                /// not meet 24 hour.//
                            } else {
                                mAds.child(mAuth.getUid())
                                        .child("interads")
                                        .removeValue();
                            }



                            if (videoAdsModel.getAd1().equals("done") &&
                                    videoAdsModel.getAd2().equals("done") &&
                                    videoAdsModel.getAd3().equals("done") &&
                                    videoAdsModel.getAd4().equals("done") &&
                                    videoAdsModel.getAd5().equals("done")) {

                                DateFormat dateFormat = new SimpleDateFormat("HH", Locale.US);
                                try {
                                    Date date1 = dateFormat.parse(videoAdsModel.getLast());
                                    Date date2 = dateFormat.parse(getCurrentDate());
                                    if (date1.compareTo(date2) > 0) {
                                        Log.i("app", "Date1 is after Date2");
                                        Toast.makeText(MainActivity.this, "Not finished yet", Toast.LENGTH_SHORT).show();

                                    } else if (date1.compareTo(date2) <= 0) {

                                        if (videoAdsModel.getAd1().equals("no") &&
                                                videoAdsModel.getAd2().equals("no") &&
                                                videoAdsModel.getAd3().equals("no") &&
                                                videoAdsModel.getAd4().equals("no") &&
                                                videoAdsModel.getAd5().equals("no")) {

                                        } else {
                                            HashMap<String, Object> map = new HashMap<>();
                                            map.put("ad1", "no");
                                            map.put("ad2", "no");
                                            map.put("ad3", "no");
                                            map.put("ad4", "no");
                                            map.put("ad5", "no");
                                            map.put("last", "");

                                            mAds.child(mAuth.getUid())
                                                    .child("interads")
                                                    .updateChildren(map);

                                        }
                                    }


                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
    private void getClickAdsData() {
        mAds.child(mAuth.getUid())
                .child("clickads")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            videoAdsModel = snapshot.getValue(VideoAdsModel.class);


                            if (getTimeDate().compareTo(videoAdsModel.getTime()) < 1) {
                                /// not meet 24 hour.//
                            } else {
                                mAds.child(mAuth.getUid())
                                        .child("clickads")
                                        .removeValue();
                            }



                            if (videoAdsModel.getAd1().equals("done") &&
                                    videoAdsModel.getAd2().equals("done") &&
                                    videoAdsModel.getAd3().equals("done") &&
                                    videoAdsModel.getAd4().equals("done") &&
                                    videoAdsModel.getAd5().equals("done")) {

                                DateFormat dateFormat = new SimpleDateFormat("HH", Locale.US);
                                try {
                                    Date date1 = dateFormat.parse(videoAdsModel.getLast());
                                    Date date2 = dateFormat.parse(getCurrentDate());
                                    if (date1.compareTo(date2) > 0) {
                                        Log.i("app", "Date1 is after Date2");
                                        Toast.makeText(MainActivity.this, "Not finished yet", Toast.LENGTH_SHORT).show();

                                    } else if (date1.compareTo(date2) <= 0) {

                                        if (videoAdsModel.getAd1().equals("no") &&
                                                videoAdsModel.getAd2().equals("no") &&
                                                videoAdsModel.getAd3().equals("no") &&
                                                videoAdsModel.getAd4().equals("no") &&
                                                videoAdsModel.getAd5().equals("no")) {

                                        } else {
                                            HashMap<String, Object> map = new HashMap<>();
                                            map.put("ad1", "no");
                                            map.put("ad2", "no");
                                            map.put("ad3", "no");
                                            map.put("ad4", "no");
                                            map.put("ad5", "no");
                                            map.put("last", "");

                                            mAds.child(mAuth.getUid())
                                                    .child("clickads")
                                                    .updateChildren(map);

                                        }
                                    }


                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
    private void getAppcofig() {
        mConfig.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    appconfig = snapshot.getValue(Appconfig.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mPlan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    planModle=snapshot.getValue(PlanModle.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mRef.child(mAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        userModels=snapshot.getValue(UserModels.class);
                        if (userModels.getAccount().equals("free")){
                            USER_COIN=planModle.getFreeuser();
                        }else {
                            USER_COIN=planModle.getPaiduser();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }

    //getting slider image data from database
    private void loadSlider() {
        mSlider.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDialog.dismiss();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        SliderItem sliderItem = dataSnapshot.getValue(SliderItem.class);
                        sliderItemArrayList.add(sliderItem);
                    }
                    sliderAdapterExample = new SliderAdapterExample(getApplicationContext());
                    sliderAdapterExample.renewItems(sliderItemArrayList);
                    binding.imageSlider.setSliderAdapter(sliderAdapterExample);
                    binding.imageSlider.startAutoCycle();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
            }
        });
    }

    private String getTimeDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    private String getCurrentDate() {
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

        return String.valueOf(calendar.getTime().getHours());
    }
}