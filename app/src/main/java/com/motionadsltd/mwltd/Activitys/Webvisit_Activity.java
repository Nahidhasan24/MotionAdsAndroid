package com.motionadsltd.mwltd.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdOptions;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonyReward;
import com.adcolony.sdk.AdColonyRewardListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.InitializationListener;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;
import com.motionadsltd.mwltd.Models.InterAds;
import com.motionadsltd.mwltd.Models.UserModels;
import com.motionadsltd.mwltd.Models.VisiteAdsModle;
import com.motionadsltd.mwltd.R;
import com.motionadsltd.mwltd.databinding.ActivityWebvisitBinding;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.VideoListener;

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
    int GET_USER_COIN;
    UserModels userModels;
    AdColonyInterstitial ad;
    AdColonyInterstitialListener listener;
    AdColonyAdOptions adOptions;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWebvisitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        GET_USER_COIN=getIntent().getIntExtra("coin",0);
        progressDialog=new ProgressDialog(Webvisit_Activity.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        mAuth= FirebaseAuth.getInstance();
        mRef= FirebaseDatabase.getInstance().getReference().child("ads");
        mUser= FirebaseDatabase.getInstance().getReference().child("users");
        progressDialog.show();


        StartAppSDK.init(this, getString(R.string.startapp_app_id), false);
        AdColony.configure(Webvisit_Activity.this,getString(R.string.ad_colony_app_id),getString(R.string.ad_colony_zone_id));
        AdColony.setRewardListener(new AdColonyRewardListener() {
            @Override
            public void onReward(@NonNull AdColonyReward adColonyReward) {

            }
        });
        IronSource.init(Webvisit_Activity.this, getString(R.string.ironsource_id), new InitializationListener() {
            @Override
            public void onInitializationComplete() {
                Toast.makeText(Webvisit_Activity.this, "Inited", Toast.LENGTH_SHORT).show();
            }
        });
        IronSource.loadInterstitial();
        checkButtons();
        getUserData();
        binding.web1.setOnClickListener(v->{

            AdColonyInterstitialListener listener=new AdColonyInterstitialListener() {
                @Override
                public void onRequestFilled(AdColonyInterstitial adColonyInterstitial) {
                    ad=adColonyInterstitial;
                    ad.show();

                }

                @Override
                public void onClosed(AdColonyInterstitial ad) {
                    super.onClosed(ad);

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

                }
            };
            AdColony.requestInterstitial(getString(R.string.ad_colony_zone_id),listener,adOptions);



        

        });
        binding.web2.setOnClickListener(v->{
            IronSource.setInterstitialListener(new InterstitialListener() {
                /**
                 * Invoked when Interstitial Ad is ready to be shown after load function was called.
                 */
                @Override
                public void onInterstitialAdReady() {
                }
                /**
                 * invoked when there is no Interstitial Ad available after calling load function.
                 */
                @Override
                public void onInterstitialAdLoadFailed(IronSourceError error) {
                }
                /**
                 * Invoked when the Interstitial Ad Unit is opened
                 */
                @Override
                public void onInterstitialAdOpened() {
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
                }
                /*
                 * Invoked when the ad is closed and the user is about to return to the application.
                 */
                @Override
                public void onInterstitialAdClosed() {
                }
                /**
                 * Invoked when Interstitial ad failed to show.
                 * @param error - An object which represents the reason of showInterstitial failure.
                 */
                @Override
                public void onInterstitialAdShowFailed(IronSourceError error) {
                }
                /*
                 * Invoked when the end user clicked on the interstitial ad, for supported networks only.
                 */
                @Override
                public void onInterstitialAdClicked() {
                }
                /** Invoked right before the Interstitial screen is about to open.
                 *  NOTE - This event is available only for some of the networks.
                 *  You should NOT treat this event as an interstitial impression, but rather use InterstitialAdOpenedEvent
                 */
                @Override
                public void onInterstitialAdShowSucceeded() {
                }
            });
            if (IronSource.isInterstitialReady()){
                IronSource.showInterstitial();
            }else{
                Toast.makeText(this, "Not Ready", Toast.LENGTH_SHORT).show();
            }



        });
        binding.web3.setOnClickListener(v->{

            final StartAppAd rewardedVideo = new StartAppAd(this);

            rewardedVideo.setVideoListener(new VideoListener() {
                @Override
                public void onVideoCompleted() {
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

                }
            });

            rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                @Override
                public void onReceiveAd(Ad ad) {
                    rewardedVideo.showAd();
                }

                @Override
                public void onFailedToReceiveAd(Ad ad) {
                    Toast.makeText(getApplicationContext(), "Can't show rewarded video", Toast.LENGTH_SHORT).show();
                }

            });


        });
        binding.web4.setOnClickListener(v->{

            AdColonyInterstitialListener listener=new AdColonyInterstitialListener() {
                @Override
                public void onRequestFilled(AdColonyInterstitial adColonyInterstitial) {
                    ad=adColonyInterstitial;
                    ad.show();

                }

                @Override
                public void onClosed(AdColonyInterstitial ad) {
                    super.onClosed(ad);

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

                }
            };
            AdColony.requestInterstitial(getString(R.string.ad_colony_zone_id),listener,adOptions);


        });
        binding.web5.setOnClickListener(v->{
            //check ads click//
            final StartAppAd rewardedVideo = new StartAppAd(this);

            rewardedVideo.setVideoListener(new VideoListener() {
                @Override
                public void onVideoCompleted() {
                    updateData();
                }
            });

            rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                @Override
                public void onReceiveAd(Ad ad) {
                    rewardedVideo.showAd();
                }

                @Override
                public void onFailedToReceiveAd(Ad ad) {
                    Toast.makeText(getApplicationContext(), "Can't show rewarded video", Toast.LENGTH_SHORT).show();
                }

            });



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

                                                    int mainCoin=userModels.getCoin()+GET_USER_COIN;
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
    protected void onResume() {
        super.onResume();
        IronSource.onResume(this);
    }
    protected void onPause() {
        super.onPause();
        IronSource.onPause(this);
    }
}