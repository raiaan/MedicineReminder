package com.example.mymedcine.splashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.mymedcine.MainActivity;
import com.example.mymedcine.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler(Looper.myLooper());
        handler.postDelayed(new MyRunnable(),3000);

    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {
            Intent intent = new Intent(SplashScreen.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}