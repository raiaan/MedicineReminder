package com.example.mymedcine.utils;

import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mymedcine.R;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.LastTime;
import com.example.mymedcine.model.RemindingTimes;

import java.util.ArrayList;

public class ViewDrugConvertor {
    private static String drugName;
    private static String type;
    public static Drug convertToDrug(View view){
        drugName= ((TextView) view.findViewById(R.id.edit_drug_name)).getText().toString();
        type= ((Spinner) view.findViewById(R.id.edit_drug_types)).getSelectedItem().toString();

        ArrayList<String> hours = new ArrayList<>();
        hours.add("4:25:7 PM");
        hours.add("4:25:7 PM");
        hours.add("4:25:7 PM");
        RemindingTimes remindingTimes = new RemindingTimes("daily",hours);
        ArrayList<String> instruction = new ArrayList<>();
        instruction.add("after dinner");
        ArrayList<String> left= new ArrayList<>();
        left.add("3");
        left.add("refill at 3 pm");
        return new Drug(drugName,type,"gm","500","active",null,remindingTimes,instruction,"hair falls",null);
    }

}
