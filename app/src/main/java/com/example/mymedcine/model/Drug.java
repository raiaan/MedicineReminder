package com.example.mymedcine.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
@Entity(tableName = "drugTable")
public class Drug {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "type")
    public String type;
    @ColumnInfo(name = "strongValue")
    public String strongValue;
    @ColumnInfo(name = "strongUnit")
    public String strongUnit;
    @ColumnInfo(name = "state")
    public String state;
    @ColumnInfo(name = "remindingTimes")
    @Ignore
    public RemindingTimes remindingTimes;
    @ColumnInfo(name = "instructions")
    @Ignore
    public ArrayList<String> instructions;
    @ColumnInfo(name = "lastTime")
    @Ignore
    public LastTime lastTime;

    @ColumnInfo(name = "reasons")
    public String reasons;
    @ColumnInfo(name = "left")
    public String left;

    public Drug() {
    }

    public Drug(String name, String type, String strongValue, String strongUnit, String state, LastTime lastTime, RemindingTimes remindingTimes, ArrayList<String> instructions, String reasons, String left) {
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

    public LastTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LastTime lastTime) {
        this.lastTime = lastTime;
    }

    public RemindingTimes getRemindingTimes() {
        return remindingTimes;
    }

    public void setRemindingTimes(RemindingTimes remindingTimes) {
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