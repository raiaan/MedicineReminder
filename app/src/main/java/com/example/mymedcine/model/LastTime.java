package com.example.mymedcine.model;

import androidx.annotation.NonNull;

public class LastTime {
    public String time;
    public String state;
    public String owner;

    public LastTime() {
    }

    public LastTime(String time, String state, String owner) {
        this.time = time;
        this.state = state;
        this.owner = owner;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    @NonNull
    @Override
    public String toString() {
        if (owner != null)
            return getTime() + " By "+getOwner();
        return getTime();
    }
}
