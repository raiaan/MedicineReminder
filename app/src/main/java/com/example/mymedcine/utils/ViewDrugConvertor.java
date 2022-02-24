package com.example.mymedcine.utils;

import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.mymedcine.R;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.LastTime;
import com.example.mymedcine.model.RemindingTimes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ViewDrugConvertor {
    private static String drugName;
    private static String type;
    private static Boolean chronicDisease;
    private static String condition;
    private static String instructions;
    public static Drug convertToDrug(View view){
        Drug drug = new Drug();
        drugName= ((TextView) view.findViewById(R.id.edit_drug_name)).getText().toString();
        type= ((Spinner) view.findViewById(R.id.edit_drug_types)).getSelectedItem().toString();
        chronicDisease = ((Switch)view.findViewById(R.id.edit_drug_chronic_disease)).isChecked();
        condition = ((TextView)view.findViewById(R.id.edit_drug_condition)).getText().toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ArrayList<String> left= new ArrayList<>();
        left.add("3");
        left.add("refill at 3 pm");
        drug.setName(drugName);
        drug.setType(type);
        drug.setChoronic(chronicDisease);
        drug.setReasons(condition);
        drug.setHours(getCompleteTimes(view));
        drug.setOccurrence("daily");
        drug.setStartDate(dtf.format(now));
        drug.setEndDate(""+convertDateToString(view));
        drug.setInstructions(new ArrayList<>(getInstructions(view)));
        drug.setState("active");
        drug.setStrongUnit("500");
        drug.setStrongValue("g");
        return drug;
    }
    private static ArrayList<Long> getCompleteTimes(View view){
        String hours_repeat = ( (Spinner)view.findViewById(R.id.edit_drug_reminding_hours_per_day) )
                .getSelectedItem().toString();
        ArrayList<Long> hours = new ArrayList<>();
        if (hours_repeat.equals("once a day")){
            hours.add(getCalender(view, R.id.hours_picker_1));
        }
        else if (hours_repeat.equals("twice a day")){
            hours.add(getCalender(view,R.id.hours_picker_2));
            hours.add( getCalender(view, R.id.hours_picker_2) );
        }
        else if (hours_repeat.equals("three times a day")){
            hours.add( getCalender(view, R.id.hours_picker_1));
            hours.add(getCalender(view, R.id.hours_picker_2));
            hours.add(getCalender(view, R.id.hours_picker_3));
        }
        return hours;
    }
    private static ArrayList<String> getInstructions(View view){
        int instructionID = ((RadioGroup)view.findViewById(R.id.edit_drug_instruction_radio_group)).getCheckedRadioButtonId();
        ArrayList<String> instruction = new ArrayList<>();
        switch (instructionID){
            case R.id.edit_drug_before_eating:
                instructions =((RadioButton)view.findViewById(R.id.edit_drug_before_eating)).getText().toString();
                break;
            case R.id.edit_drug_after_eating:
                instructions =((RadioButton)view.findViewById(R.id.edit_drug_after_eating)).getText().toString();
                break;
            case R.id.edit_drug_while_eating:
                instructions =((RadioButton)view.findViewById(R.id.edit_drug_while_eating)).getText().toString();
                break;
            case R.id.edit_drug_instruct_doesnt_matter:
                instructions =((RadioButton)view.findViewById(R.id.edit_drug_instruct_doesnt_matter)).getText().toString();
                break;
        }
        instruction.add(instructions);
        String tempmore = ((TextView)view.findViewById(R.id.edit_drug_instruct_other)).getText().toString();
        if (!tempmore.isEmpty() && tempmore != null) {
            instruction.add(tempmore);
        }
        return instruction;
    }
    public static long convertDateToString(View parentView){
        int year =  ( (DatePicker)parentView.findViewById(R.id.edit_drug_end_date_picker_date) ).getYear();
        int month = ( (DatePicker)parentView.findViewById(R.id.edit_drug_end_date_picker_date) ).getMonth();
        int day = ( (DatePicker)parentView.findViewById(R.id.edit_drug_end_date_picker_date) ).getDayOfMonth();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTimeInMillis();
    }
    private static Long getCalender(View view, int id){
        Calendar calendar = Calendar.getInstance();
        TimePicker timePicker = (TimePicker)view.findViewById(id);
        calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
        return calendar.getTimeInMillis();
    }
}
