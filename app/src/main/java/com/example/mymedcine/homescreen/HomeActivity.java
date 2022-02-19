package com.example.mymedcine.homescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mymedcine.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        bottomNavigationView = findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setOnItemSelectedListener(item ->
//        {switch(item.getItemId()) {
//            case R.id.home_fragment:
//                Toast.makeText(this, "Home Page", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.medicine_fragment:
//                Toast.makeText(this, "Medicine Page", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.taker_fragment:
//                Toast.makeText(this, "Taker Page", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return false;
//        });
    }

}