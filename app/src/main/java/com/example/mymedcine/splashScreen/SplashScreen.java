package com.example.mymedcine.splashScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.mymedcine.MainActivity;
import com.example.mymedcine.R;
import com.example.mymedcine.homescreen.HomeActivity;
import com.example.mymedcine.login.view.LoginActivity;
import com.example.mymedcine.utils.SharedPreferencesUtils;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    boolean login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences(SharedPreferencesUtils.FILE_NAME, MODE_PRIVATE);

        if(savedInstanceState != null){
            login = savedInstanceState.getBoolean(SharedPreferencesUtils.LOGIN_KEY);
        } else {
            login = false;
        }

        Handler handler = new Handler(Looper.myLooper());
        handler.postDelayed(new MyRunnable(),3000);

    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {
            Intent intent;
            Toast.makeText(SplashScreen.this, ""+login, Toast.LENGTH_SHORT).show();
            if (login){
                intent = new Intent(SplashScreen.this, HomeActivity.class);
            }else {
                intent = new Intent(SplashScreen.this, LoginActivity.class);
            }
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SharedPreferencesUtils.LOGIN_KEY,
                sharedPreferences.getBoolean(SharedPreferencesUtils.LOGIN_KEY, false));
    }
}