package com.example.mymedcine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mymedcine.database.AppDataBase;
import com.example.mymedcine.drugdetails.view.DisplayDrugDetailsFragment;
import com.example.mymedcine.drugdetails.view.DrugDisplayer;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.LastTime;
import com.example.mymedcine.model.Prescription;
import com.example.mymedcine.model.RemindingTimes;
import com.example.mymedcine.utils.Communator;

import java.util.ArrayList;

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