package com.example.mymedcine.model;

public class RemindingTime {
    public String date;
    public String state;
    public String owner;

    public RemindingTime(String date, String state, String owner) {
        this.date = date;
        this.state = state;
        this.owner = owner;
    }

    public RemindingTime() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
