package com.example.mymedcine.model;

import java.util.ArrayList;

public class RemindingTimes {
    public String occurrence;
    public ArrayList<String> hours;

    public RemindingTimes(String occurrence, ArrayList<String> hours) {
        this.occurrence = occurrence;
        this.hours = hours;
    }

    public RemindingTimes() {
    }

    public String getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    public String getHours() {
        String hoursTotalTXT = null;
        for(String hour: hours){
            if(hoursTotalTXT ==null){
                hoursTotalTXT = hour;
            }else hoursTotalTXT += "\n"+hour;
        }
        return hoursTotalTXT;
    }

    public void setHours(ArrayList<String> hours) {
        this.hours = hours;
    }
}
