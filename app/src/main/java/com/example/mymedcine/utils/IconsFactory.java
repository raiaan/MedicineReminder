package com.example.mymedcine.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.mymedcine.R;

public class IconsFactory {
    public static Drawable getIcon(Context context , String type){
        Drawable icon = null;
        switch (type){
            case "pill":
                icon = context.getResources().getDrawable(R.drawable.ic_pills);
                break;
            case "vaccine":
                icon = context.getResources().getDrawable(R.drawable.vaccine);
                break;
            case "transfusion":
                icon = context.getResources().getDrawable(R.drawable.transfusion);
                break;
            case "spoon":
                icon = context.getResources().getDrawable(R.drawable.spoon);
                break;
            case "respirator":
                icon = context.getResources().getDrawable(R.drawable.respirator);
                break;
            case "ointment":
                icon = context.getResources().getDrawable(R.drawable.ointment);
                break;
            case"dropper":
                icon = context.getResources().getDrawable(R.drawable.ic_water_drop_black_24dp);
                break;
        }
        return icon;
    }

    public static int getIconID(String type){
        int icon = 0;
        switch (type){
            case "pill":
                icon = R.drawable.ic_pills;
                break;
            case "vaccine":
                icon = R.drawable.vaccine;
                break;
            case "transfusion":
                icon = R.drawable.transfusion;
                break;
            case "spoon":
                icon = R.drawable.spoon;
                break;
            case "respirator":
                icon = R.drawable.respirator;
                break;
            case "ointment":
                icon = R.drawable.ointment;
                break;
            case"dropper":
                icon = R.drawable.ic_water_drop_black_24dp;
                break;
        }
        return icon;
    }
    public static String [] getDrugIconsNames(){
        return new String[]{"pill","vaccine","transfusion","spoon","respirator","ointment","dropper"};
    }
}
