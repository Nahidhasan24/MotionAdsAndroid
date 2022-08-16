package com.motionadsltd.mwltd.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.motionadsltd.mwltd.Models.TeamsModel;
import com.motionadsltd.mwltd.Models.UserModels;
import com.motionadsltd.mwltd.R;
import com.motionadsltd.mwltd.databinding.ActivityRankBinding;

import java.util.ArrayList;

public class Rank_Activity extends AppCompatActivity {

    ActivityRankBinding binding;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    DatabaseReference mTeam;
    UserModels userModels;
    ArrayList<TeamsModel> teamsModelArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRankBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth=FirebaseAuth.getInstance();
        mRef= FirebaseDatabase.getInstance().getReference().child("users");
        mTeam= FirebaseDatabase.getInstance().getReference().child("teams");
        getData();
    }

    private void getData() {
        mRef.child(mAuth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        userModels=snapshot.getValue(UserModels.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        mTeam.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    TeamsModel teamsModel=dataSnapshot.getValue(TeamsModel.class);
                    if (teamsModel.getTeam().equals(userModels.getTeam())||
                            teamsModel.getTeam().equals(userModels.getRefercode())){
                        teamsModelArrayList.add(teamsModel);
                       loadData(teamsModel);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadData(TeamsModel teamsModel) {
        binding.teamTV.setText("Team Name : "+teamsModel.getTeam());
        binding.teamLeaderTV.setText("Team Leader : "+teamsModel.getTeam());
        binding.teamTotalMamberTV.setText("Total Member : "+teamsModelArrayList.size());
    }
}