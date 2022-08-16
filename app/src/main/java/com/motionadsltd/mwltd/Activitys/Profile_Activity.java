package com.motionadsltd.mwltd.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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
import com.motionadsltd.mwltd.Models.PlanModle;
import com.motionadsltd.mwltd.Models.TeamsModel;
import com.motionadsltd.mwltd.Models.UserModels;
import com.motionadsltd.mwltd.R;
import com.motionadsltd.mwltd.databinding.ActivityProfileBinding;

import java.util.HashMap;

public class Profile_Activity extends AppCompatActivity {

    ActivityProfileBinding binding;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    DatabaseReference mPlan;
    DatabaseReference mTeam;
    UserModels userModels;
    PlanModle planModle;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog=new ProgressDialog(Profile_Activity.this);
        progressDialog.setTitle("Loading....");
        progressDialog.setCancelable(false);
        mAuth=FirebaseAuth.getInstance();
        mRef= FirebaseDatabase.getInstance().getReference().child("users");
        mPlan= FirebaseDatabase.getInstance().getReference().child("plan");
        mTeam= FirebaseDatabase.getInstance().getReference().child("teams");
        progressDialog.show();
        getUserData();
        binding.refer.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),ReferActivity.class));
        });
        binding.logoutBtn.setOnClickListener(v->{
          AlertDialog.Builder builder=new AlertDialog.Builder(Profile_Activity.this);
          builder.setTitle("Logout");
          builder.setMessage("Do you want to SignOut ?");
          builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                  mAuth.signOut();
                  startActivity(new Intent(getApplicationContext(), Login_Activity.class));
                  finishAffinity();
              }
          }).setPositiveButton("No", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                  dialogInterface.dismiss();
              }
          });
          AlertDialog alertDialog=builder.create();
          alertDialog.show();
        });
        //upgrading plan
        binding.upgreadBtn.setOnClickListener(v->{
           if (userModels.getAccount().equals("free")){
               if (userModels.getCoin()>=planModle.getPrice()){
                   showDialogUser();
               }else{
                   Toast.makeText(this, "not enough coin !", Toast.LENGTH_SHORT).show();
               }
           }else{
               Toast.makeText(this, "Your Account is Already Paid", Toast.LENGTH_SHORT).show();
           }
        });
        binding.teamBtn.setOnClickListener(v->{
            mTeam.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            TeamsModel teamsModel = dataSnapshot.getValue(TeamsModel.class);
                            if (teamsModel.getTeam().equals(userModels.getRefercode())) {
                                startActivity(new Intent(getApplicationContext(),Rank_Activity.class));
                            }else if (!userModels.getTeam().equals("")){
                                startActivity(new Intent(getApplicationContext(),Rank_Activity.class));
                            }else{
                                Toast.makeText(getApplicationContext(), "You don't have team", Toast.LENGTH_SHORT).show();
                            }
                            break;

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        });
    }


    private void showDialogUser() {
        AlertDialog.Builder builder=new AlertDialog.Builder(Profile_Activity.this);
        builder.setTitle("Upgrade Plan !");
        builder.setMessage("do you want to upgrade you plan by "+planModle.getPrice()+" coin ?");
        builder.setCancelable(false);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                progressDialog.show();
                int mainPoint =userModels.getCoin()-planModle.getPrice();
                HashMap<String,Object> map=new HashMap<>();
                map.put("coin",mainPoint);
                map.put("account","paid");
                mRef.child(mAuth.getUid())
                        .updateChildren(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.dismiss();
                               if (task.isSuccessful()){
                                   Toast.makeText(Profile_Activity.this, "Plan Upgrade Successfully", Toast.LENGTH_SHORT).show();
                               }else{
                                   Toast.makeText(Profile_Activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                               }
                            }
                        });
            }
        }).setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    private void getUserData() {
        mRef.child(mAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        progressDialog.dismiss();
                        if (snapshot.exists()){
                            userModels=snapshot.getValue(UserModels.class);
                            binding.profileCoinTV.setText("Balance : "+userModels.getCoin()+" coin");
                            binding.profileMailTV.setText("Mail : "+userModels.getMail());
                            binding.profileNameTV.setText("Name : "+userModels.getName());
                            binding.profilePlan.setText("Account Type : "+userModels.getAccount());
                            binding.profileNumberTV.setText("Number : "+userModels.getPhone());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressDialog.dismiss();
                    }
                });
        mPlan.addListenerForSingleValueEvent(new ValueEventListener() {
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
    }
}