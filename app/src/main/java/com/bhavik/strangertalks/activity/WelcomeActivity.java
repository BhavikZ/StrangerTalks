package com.bhavik.strangertalks.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bhavik.strangertalks.R;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        auth=FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            goToNextActivity();
        }

        findViewById(R.id.getstarted).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivity();
            }
        });
    }

    private void goToNextActivity() {
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        finish();

    }
}