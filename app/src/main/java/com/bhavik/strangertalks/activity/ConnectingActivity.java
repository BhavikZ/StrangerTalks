package com.bhavik.strangertalks.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bhavik.strangertalks.databinding.ActivityConnectingBinding;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class ConnectingActivity extends AppCompatActivity {

    ActivityConnectingBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConnectingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();

        String profile= getIntent().getStringExtra("profile");
        Glide.with(this)
                .load(profile)
                .into(binding.profile);

    }
}