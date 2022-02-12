package com.example.mymedcine.model;

import java.util.ArrayList;

public class Drug {
    public String name;
    public String type;
    public String strongValue;
    public String strongUnit;
    public String state;
    public String lastTime;
    public ArrayList<RemindingTime> remindingTimes;
    public ArrayList<String> instructions;
    public String reasons;
    public String left;

    public Drug() {
    }

    public Drug(String name, String type, String strongValue, String strongUnit, String state, String lastTime, ArrayList<RemindingTime> remindingTimes, ArrayList<String> instructions, String reasons, String left) {
        this.name = name;
        this.type = type;
        this.strongValue = strongValue;
        this.strongUnit = strongUnit;
        this.state = state;
        this.lastTime = lastTime;
        this.remindingTimes = remindingTimes;
        this.instructions = instructions;
        this.reasons = reasons;
        this.left = left;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStrongValue() {
        return strongValue;
    }

    public void setStrongValue(String strongValue) {
        this.strongValue = strongValue;
    }

    public String getStrongUnit() {
        return strongUnit;
    }

    public void setStrongUnit(String strongUnit) {
        this.strongUnit = strongUnit;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public ArrayList<RemindingTime> getRemindingTimes() {
        return remindingTimes;
    }

    public void setRemindingTimes(ArrayList<RemindingTime> remindingTimes) {
        this.remindingTimes = remindingTimes;
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }
}
