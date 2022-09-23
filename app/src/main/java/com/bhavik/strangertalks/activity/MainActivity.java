package com.bhavik.strangertalks.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bhavik.strangertalks.databinding.ActivityMainBinding;
import com.bhavik.strangertalks.modual.User;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    long coins=0;
    String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
    private int  requestCode = 1;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        database.getReference().child("profiles")
                .child(currentUser.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                         user=snapshot.getValue(User.class);

                        coins=user.getCoins();

                        binding.coins.setText("You have :"+coins);

                        Glide.with(MainActivity.this)
                                .load(user.getProfile())
                                .into(binding.profilePicture);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        binding.findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isPermissionGranted()) {
                    if (coins > 5) {
                        Intent intent= new Intent(MainActivity.this, ConnectingActivity.class);
                        intent.putExtra("profile",user.getProfile());
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Insufficient Coins", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    askPermission();
                }
            }
        });

    }

    void askPermission(){
        ActivityCompat.requestPermissions(this,permissions,requestCode);
    }

    private  boolean isPermissionGranted(){
        for (String permission : permissions){
            if(ActivityCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }

        return true;
    }

}