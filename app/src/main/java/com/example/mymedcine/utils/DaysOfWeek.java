package com.example.mymedcine.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class DaysOfWeek {
    static final String [] weekDays = new String[]{"Sat","Sun" ,"Mon","Tue","Wed","Thur","Fri"};
    public static  ArrayList<String> getDays() {
        return new ArrayList<String>(Arrays.asList(weekDays));
    }
}
