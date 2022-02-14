package com.example.mymedcine.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.mymedcine.R;

public class IconsFactory {
    public static Drawable getIcon(Context context , String type){
        Drawable icon = null;
        switch (type){
            case "pill":
                icon = context.getResources().getDrawable(R.drawable.pill_icon);
                break;
        }
        return icon;
    }
}
