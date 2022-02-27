package com.example.mymedcine.model;

import java.util.List;

public class DrugsBerDay {
    String time;
    List<Drug> dailyDrugs;

    public DrugsBerDay(String time, List<Drug> dailyDrugs) {
        this.time = time;
        this.dailyDrugs = dailyDrugs;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Drug> getDailyDrugs() {
        return dailyDrugs;
    }

    public void setDailyDrugs(List<Drug> dailyDrugs) {
        this.dailyDrugs = dailyDrugs;
    }
}
