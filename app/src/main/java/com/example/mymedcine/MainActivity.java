package com.example.mymedcine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.example.mymedcine.drugdetails.view.DisplayDrugDetailsFragment;
import com.example.mymedcine.drugdetails.view.DrugDisplayer;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.LastTime;
import com.example.mymedcine.model.RemindingTimes;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrugDisplayer drugDisplayer;
    Drug drug;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> hours = new ArrayList<>();
        hours.add("4:25:7 PM");
        hours.add("4:25:7 PM");
        hours.add("4:25:7 PM");
        RemindingTimes remindingTimes = new RemindingTimes("every Day",hours);
        LastTime lastTime = new LastTime("20:13:00 PM","taken","ahmed");
        ArrayList<String> instruction = new ArrayList<>();
        instruction.add("after dinner");
        drug = new Drug("Pandol Extra","pill","gm","500","active",lastTime,remindingTimes,instruction,"hair falls","3");
    }
    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof DisplayDrugDetailsFragment){
            drugDisplayer = (DrugDisplayer) fragment;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (drugDisplayer!=null)
            drugDisplayer.displayDrugDetails(drug);
    }
}