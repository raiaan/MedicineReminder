package com.example.mymedcine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.example.mymedcine.drugdetails.view.DisplayDrugDetailsFragment;
import com.example.mymedcine.drugdetails.view.DrugDisplayer;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.LastTime;
import com.example.mymedcine.model.Prescription;
import com.example.mymedcine.model.RemindingTimes;
import com.example.mymedcine.utils.Communator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Communator {

    DrugDisplayer drugDisplayer;
    Prescription prescription;
    Drug drug;
    NavController navController;
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
        ArrayList<String> left= new ArrayList<>();
        left.add("3");
        left.add("refill at 3 pm");
        drug = new Drug("Pandol Extra","pill","gm","500","active",lastTime,remindingTimes,instruction,"hair falls",left);
        ArrayList<Drug> drugs = new ArrayList<>();
        drugs.add(drug);
        ArrayList<String>healthTaker = new ArrayList<>();
        healthTaker.add("heba");
        prescription = new Prescription("r@gmail.com","x min nas",healthTaker,drugs);
        navController= Navigation.findNavController(this, R.id.fragmentContainerView);
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
            drugDisplayer.displayDrugDetails(prescription,0);
    }

    @Override
    public void sendMessage(Prescription prescription) {
        Log.v("main activity","true");
        navController.popBackStack();
        navController.navigate(R.id.edit_drug_details_fragment);
    }
}