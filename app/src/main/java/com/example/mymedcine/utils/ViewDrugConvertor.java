package com.example.mymedcine.utils;

import android.util.Log;
import android.view.View;
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

public class ViewDrugConvertor {
    private static String drugName;
    private static String type;
    private static Boolean chronicDisease;
    private static String condition;
    private static String instructions;
    public static Drug convertToDrug(View view){
        int instructionID;
        Drug drug = new Drug();
        drugName= ((TextView) view.findViewById(R.id.edit_drug_name)).getText().toString();
        type= ((Spinner) view.findViewById(R.id.edit_drug_types)).getSelectedItem().toString();
        chronicDisease = ((Switch)view.findViewById(R.id.edit_drug_chronic_disease)).isChecked();
        condition = ((TextView)view.findViewById(R.id.edit_drug_condition)).getText().toString();
        instructionID = ((RadioGroup)view.findViewById(R.id.edit_drug_instruction_radio_group)).getCheckedRadioButtonId();
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

        RemindingTimes remindingTimes = new RemindingTimes("daily",getCompleteTimes(view));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        remindingTimes.setStartDate(dtf.format(now));
        ArrayList<String> left= new ArrayList<>();
        left.add("3");
        left.add("refill at 3 pm");
        drug.setName(drugName);
        drug.setType(type);
        drug.setChoronic(chronicDisease);
        drug.setReasons(condition);
        drug.setRemindingTimes(remindingTimes);
        drug.setInstructions(new ArrayList<>(instruction));
        drug.setState("active");
        drug.setStrongUnit("500");
        drug.setStrongValue("g");
        return drug;
    }
    private static ArrayList<String> getCompleteTimes(View view){
        String hours_repeat = ( (Spinner)view.findViewById(R.id.edit_drug_reminding_hours_per_day) )
                .getSelectedItem().toString();
        ArrayList<String> hours = new ArrayList<>();
        if (hours_repeat.equals("once a day")){
            hours.add(
                   ((TimePicker)view.findViewById(R.id.hours_picker_1)).getHour()+":"+
                           ((TimePicker)view.findViewById(R.id.hours_picker_1)).getMinute()+" "
            );
        }
        else if (hours_repeat.equals("twice a day")){
            hours.add(
                    ((TimePicker)view.findViewById(R.id.hours_picker_1)).getHour()+":"+
                            ((TimePicker)view.findViewById(R.id.hours_picker_1)).getMinute()+" "
            );
            hours.add(

                    ((TimePicker)view.findViewById(R.id.hours_picker_2)).getHour()+":"+
                            ((TimePicker)view.findViewById(R.id.hours_picker_2)).getMinute()+" "
            );
        }
        else if (hours_repeat.equals("")){
            hours.add(
                    ((TimePicker)view.findViewById(R.id.hours_picker_1)).getHour()+":"+
                            ((TimePicker)view.findViewById(R.id.hours_picker_1)).getMinute()+" "
            );
            hours.add(

                    ((TimePicker)view.findViewById(R.id.hours_picker_2)).getHour()+":"+
                            ((TimePicker)view.findViewById(R.id.hours_picker_2)).getMinute()+" "
            );
            hours.add(
                    ((TimePicker)view.findViewById(R.id.hours_picker_3)).getHour()+":"+
                            ((TimePicker)view.findViewById(R.id.hours_picker_3)).getMinute()+" "
            );
        }
        return hours;
    }
}
