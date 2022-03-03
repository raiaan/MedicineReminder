package com.example.mymedcine.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.example.mymedcine.database.Converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = "drugTable")
public class Drug implements Serializable {
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
    @ColumnInfo(name = "isDaily")
    public boolean isDaily;
    /*@ColumnInfo(name = "days")
    public ArrayList<String> days;*/
    @ColumnInfo(name = "remindingTimes")
    @Ignore
    public RemindingTimes remindingTimes;
    @ColumnInfo(name = "instructions")
    public ArrayList<String> instructions;
    @ColumnInfo(name = "lastTime")
    @Ignore
    public LastTime lastTime;
    @ColumnInfo(name = "reasons")
    public String reasons;
    public Boolean remindRefill;
    public int refillRemindCount;
    public long refillRemindTime;
    @ColumnInfo(name = "left")
    public int left;
    @ColumnInfo(name = "is_chronic")
    public boolean isChoronic;
    @ColumnInfo(name="occurrence")
    public String occurrence;
    public ArrayList<Long> hours;
    @ColumnInfo(name ="endDate")
    public String endDate;
    @ColumnInfo(name = "startDate")
    public String startDate;
    @ColumnInfo(name = "weekDays")
    public ArrayList<String> weekDays;
    public String lastTimeTaken ;
    public String lastTimeDoseGiver;
    public Boolean getRemindRefill() {
        return remindRefill;
    }

    public void setRemindRefill(Boolean remindRefill) {
        this.remindRefill = remindRefill;
    }

    public int getRefillRemindCount() {
        return refillRemindCount;
    }

    public void setRefillRemindCount(int refillRemindCount) {
        this.refillRemindCount = refillRemindCount;
    }

    public long getRefillRemindTime() {
        return refillRemindTime;
    }

    public void setRefillRemindTime(long refillRemindTime) {
        this.refillRemindTime = refillRemindTime;
    }

    public boolean isChoronic() {
        return isChoronic;
    }

    public String getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    public ArrayList<Long> getHours() {
        return hours;
    }
    public String getStringHours(){
        String result = null;
        if(hours != null){
            for (Long hour : hours){
                if(result == null){
                    result = String.valueOf(hour);
                }else result +="\n"+ hour;
            }
        }
        return result == null? "No Reminding Hours Added": result;
    }
    public void setHours(ArrayList<Long> hours) {
        this.hours = hours;
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

    public Drug() {
    }

    public boolean getIsChoronic() {
        return isChoronic;
    }

    public void setChoronic(boolean choronic) {
        isChoronic = choronic;
    }

    public Drug(String name, String type, String strongValue, String strongUnit, String state, LastTime lastTime, RemindingTimes remindingTimes, ArrayList<String> instructions, String reasons, int left) {
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

    public Drug(@NonNull String name, String type, String strongValue, String strongUnit, String state) {
        this.name = name;
        this.type = type;
        this.strongValue = strongValue;
        this.strongUnit = strongUnit;
        this.state = state;
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


    public String getInstructions() {
        String result= "";
        if (instructions!=null){
            for (String instruct : instructions){
                result += instruct + " ";
            }
        }
        return result;

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

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public boolean isDaily() {
        return isDaily;
    }

    public void setDaily(boolean daily) {
        isDaily = daily;
    }

    public String getWeekDays() {
        String result= "";
        if (weekDays!=null){
            for (String day : weekDays){
                result += day + " ";
            }
        }
        return result;
    }

    public void setWeekDays(ArrayList<String> weekDays) {
        this.weekDays = weekDays;
    }

    public String[] getHoursAsTimes(){
        String[] times = new String[getHours().size()];
        for(int i = 0; i < times.length; i ++){
            long millis = getHours().get(i);
            Date dete = new Date(millis);
            times[i] = dete.toString().substring(11,16);
        }
        return times;
    }
}