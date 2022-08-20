package com.motionadsltd.mwltd.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.motionadsltd.mwltd.Models.ReferModle;
import com.motionadsltd.mwltd.Models.TeamsModel;
import com.motionadsltd.mwltd.Models.UserModels;
import com.motionadsltd.mwltd.R;
import com.motionadsltd.mwltd.databinding.ActivityReferBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class ReferActivity extends AppCompatActivity {

    ActivityReferBinding binding;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    DatabaseReference mTeam;
    DatabaseReference mRefer;
    UserModels userModels;
    String uid;
    Boolean isTeam=false;
    ArrayList<ReferModle> referModleArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityReferBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth=FirebaseAuth.getInstance();
        mRef= FirebaseDatabase.getInstance().getReference().child("users");
        mTeam= FirebaseDatabase.getInstance().getReference().child("teams");
        mRefer= FirebaseDatabase.getInstance().getReference().child("refer");
        getData();
        binding.copyRef.setOnClickListener(v->{
            setClipboard(getApplicationContext(),userModels.getRefercode());
        });
        binding.shareRef.setOnClickListener(v->{
            String msg="Hello My refer code "+userModels.getRefercode()+" And Join My Team "+sendRevire();
            shareText(msg);
        });

    }

    private void loadDiaload() {
        Dialog dialog=new Dialog(ReferActivity.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.refer_dialog);
        dialog.show();
        EditText editText=dialog.findViewById(R.id.refer_et);
        dialog.findViewById(R.id.referBtn)
                .setOnClickListener(v->{
                    String refer_code=editText.getText().toString();
                    if (refer_code.isEmpty()){
                        Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                        return;
                    }if (refer_code.equals(userModels.getRefercode())){
                        Toast.makeText(this, "You can't use your code", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                UserModels checkModle=dataSnapshot.getValue(UserModels.class);
                                if (checkModle.getRefercode().equals(refer_code)){
                                    uid=checkModle.getUid();
                                    dialog.dismiss();
                                    wokeRefer(checkModle);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                });

    }

    private void wokeRefer(UserModels model) {
        ReferModle referModle=new ReferModle(model.getRefercode(),model.getUid());
        mRefer.child(mAuth.getUid())
                .setValue(referModle)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            mTeam.child(model.getTeam())
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                TeamsModel teamsModel=new TeamsModel(userModels.getRefercode(),mAuth.getUid(),model.getRefercode());
                                                mTeam.child(mAuth.getUid())
                                                        .setValue(teamsModel)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()){
                                                                    int refe=model.getRefercount()+1;
                                                                    HashMap<String,Object> hashMap=new HashMap<>();
                                                                    hashMap.put("refercount",refe);

                                                                    mRef.child(uid)
                                                                            .updateChildren(hashMap)
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    if (task.isSuccessful()){
                                                                                       HashMap<String,Object> map=new HashMap<>();
                                                                                       map.put("referby",model.getRefercode());
                                                                                       map.put("team",model.getRefercode());
                                                                                       if (userModels.getAccount().equals("paid")&&model.getAccount().equals("paid")){
                                                                                           int mainPoint=model.getCoin()+30;
                                                                                           map.put("coin",mainPoint);
                                                                                       }
                                                                                       mRef.child(mAuth.getUid())
                                                                                               .updateChildren(map)
                                                                                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                   @Override
                                                                                                   public void onComplete(@NonNull Task<Void> task) {
                                                                                                       if (task.isSuccessful()){
                                                                                                           Toast.makeText(ReferActivity.this, "Refer Done", Toast.LENGTH_SHORT).show();
                                                                                                       }else{
                                                                                                           Toast.makeText(ReferActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                                                                                       }
                                                                                                   }
                                                                                               });
                                                                                    }else{
                                                                                        Toast.makeText(ReferActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                }
                                                                            });

                                                                }else{
                                                                    Toast.makeText(ReferActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });

                                            }


                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                        }else{
                            Toast.makeText(ReferActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
    private void getData() {

        mRef.child(mAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        userModels=snapshot.getValue(UserModels.class);
                        binding.referCodeTV.setText(""+userModels.getRefercode());
                        binding.totalRefer.setText(""+userModels.getRefercount());

                        mTeam.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                        TeamsModel teamsModel=dataSnapshot.getValue(TeamsModel.class);
                                        if (teamsModel.getTeam().equals(userModels.getRefercode())){
                                            isTeam=true;
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        if (userModels.getReferby().equals("")&& !isTeam){
                                loadDiaload();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
    private void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
        Toast.makeText(context, "Copy", Toast.LENGTH_SHORT).show();
    }
    private String sendRevire(){
        final String appPackageName = getApplicationContext().getPackageName();
        String url="https://play.google.com/store/apps/details?id=" + appPackageName;
        return  url;
    }
    public void shareText(String body) {
        Intent txtIntent = new Intent(android.content.Intent.ACTION_SEND);
        txtIntent .setType("text/plain");
        txtIntent .putExtra(android.content.Intent.EXTRA_SUBJECT, "Invite And Eran");
        txtIntent .putExtra(android.content.Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(txtIntent ,"Share"));
    }
}