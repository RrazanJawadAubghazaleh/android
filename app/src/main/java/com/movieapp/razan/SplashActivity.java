package com.movieapp.razan;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.movieapp.razan.home.ui.HomeActivity;
import com.movieapp.razan.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    Animation topAnimation, bottomAnimation;
    ImageView imageView;
    TextView textViewAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.botttom_animation);

        imageView = findViewById(R.id.imageViewCircal);
        textViewAppName = findViewById(R.id.textViewAppName);
        imageView.setAnimation(topAnimation);
        textViewAppName.setAnimation(bottomAnimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 5000);
    }
}