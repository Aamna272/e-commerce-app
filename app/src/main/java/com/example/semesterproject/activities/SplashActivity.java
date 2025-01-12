package com.example.semesterproject.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.semesterproject.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo);
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(2000); // 2 seconds
        logo.startAnimation(fadeIn);

        // Delay before navigating to the next screen
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }, 3000); // 3 seconds delay
    }
}