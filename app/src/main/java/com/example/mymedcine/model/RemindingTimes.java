package com.example.mymedcine.model;

import java.util.ArrayList;

public class RemindingTimes {
    public String occurrence;
    public ArrayList<String> hours;
    public String endDate;
    public String startDate;
    public RemindingTimes(String occurrence ,ArrayList<String> hours) {
        this.occurrence = occurrence;
        this.hours = hours;
    }

    public RemindingTimes() {

    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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
