package com.example.mymedcine;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymedcine.model.Prescription;
import com.example.mymedcine.utils.Communator;


public class MainActivity extends AppCompatActivity implements Communator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void sendMessage(Prescription prescription) {

    }
}