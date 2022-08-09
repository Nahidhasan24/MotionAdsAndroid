package com.motionadsltd.mwltd.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.motionadsltd.mwltd.Adapters.SliderAdapterExample;
import com.motionadsltd.mwltd.Models.SliderItem;
import com.motionadsltd.mwltd.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    DatabaseReference mSlider;
    ProgressDialog progressDialog;
    SliderAdapterExample sliderAdapterExample;
    ArrayList<SliderItem>sliderItemArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("users");
        mSlider = FirebaseDatabase.getInstance().getReference().child("slider");
        // init progress dialog
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading....");
        progressDialog.show();
        loadSlider();
        binding.videoAdsBtn.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), Videoads_Activity.class));
            ///
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        });

        ///
        binding.walletbtn.setOnClickListener(view -> {
            Intent in=new Intent( MainActivity.this, Wallet_Activity.class);
            startActivity(in);
        });

        ///
        binding.aboutusbtn.setOnClickListener(view -> {

            Intent in=new Intent( MainActivity.this, About_Activity.class);
            startActivity(in);
        });
        ///
        binding.noticebtn.setOnClickListener(view -> {

            Intent in=new Intent( MainActivity.this, Notice_Activity.class);
            startActivity(in);
        });
        ///
        binding.helpcenterbtn.setOnClickListener(view -> {

            Intent in=new Intent( MainActivity.this, HelpCenter_Activity.class);
            startActivity(in);
        });

        ///
        binding.viewadsbtn.setOnClickListener(view -> {

            Intent in=new Intent( MainActivity.this, Clickads_Activity.class);
            startActivity(in);
        });
        ///
        binding.visitadsbtn.setOnClickListener(view -> {

            Intent in=new Intent( MainActivity.this, Webvisit_Activity.class);
            startActivity(in);
        });

        ///
        binding.addmoney.setOnClickListener(view -> {

            Intent in=new Intent( MainActivity.this, AddMoneyActivity.class);
            startActivity(in);
        });
        binding.profileBtn.setOnClickListener(c->{
            startActivity(new Intent(getApplicationContext(),Profile_Activity.class));
        });






    }
    //getting slider image data from database
    private void loadSlider() {
        mSlider.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDialog.dismiss();
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        SliderItem sliderItem=dataSnapshot.getValue(SliderItem.class);
                        sliderItemArrayList.add(sliderItem);
                    }
                    sliderAdapterExample=new SliderAdapterExample(getApplicationContext());
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
}