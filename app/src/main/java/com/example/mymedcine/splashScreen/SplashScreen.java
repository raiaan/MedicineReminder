package com.example.mymedcine.splashScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AnimationUtils;

import com.example.mymedcine.MainActivity;
import com.example.mymedcine.R;
import com.example.mymedcine.homescreen.HomeActivity;
import com.example.mymedcine.login.view.LoginActivity;

public class SplashScreen extends AppCompatActivity {
    AppCompatImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        image = findViewById(R.id.splash_icom);
        image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate) );
        Handler handler = new Handler(Looper.myLooper());
        handler.postDelayed(new MyRunnable(),3000);

    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {
            Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}