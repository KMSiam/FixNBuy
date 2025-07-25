package com.example.fixnbuy.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fixnbuy.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    ImageView splashScreenIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        splashScreenIcon = findViewById(R.id.appLogo);
        Animation splashScreenIconScale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        splashScreenIcon.startAnimation(splashScreenIconScale);

        Intent splashIntent = new Intent(getApplicationContext(), LoginActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(splashIntent);
                finish();
            }
        }, 3000);


    }
}