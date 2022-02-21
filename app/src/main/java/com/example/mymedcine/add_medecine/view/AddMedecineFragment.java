package com.example.mymedcine.add_medecine.view;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.mymedcine.MainActivity;
import com.example.mymedcine.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddMedecineFragment extends Fragment implements AddMedecineInterface{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView timer_txt;
    TextView number_txt;
    TextView schedualing_txt;
    TextView selectdate;
    TextView Presstoadjust_txt;
    Button nextlayoutone,nextlayoutthree,nextlayoutfour,moreopitionsBtn,doneBtn;
    LinearLayout medicineNameLayout,reminderTime_layout,schedualing_layout,medicineSymbol_layout,strength_layout,instructions_layout,refilling_layout;

    int timerHour , timerMinute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_medecine, container, false);
        //initUI(view);
        initGoneViews();
        handleViewsVisiblity();
        handleClickListener();
        return view;
    }
    private void initUI(View view){
//        doneBtn = view.findViewById(R.id.doneBtn);
//        Presstoadjust_txt = view.findViewById(R.id.Presstoadjust_txt);
//        nextlayoutone = view.findViewById(R.id.afterLayout1);
//        nextlayoutthree = view.findViewById(R.id.afterLayout3);
//        nextlayoutfour = view.findViewById(R.id.afterLayout4);
//        moreopitionsBtn = view.findViewById(R.id.moreopitions);
//        medicineNameLayout = view.findViewById(R.id.midName_layout);
//        reminderTime_layout = view.findViewById(R.id.remindertimes_layout);
//        schedualing_layout = view.findViewById(R.id.scheduling_layout);
//        medicineSymbol_layout = view.findViewById(R.id.medicinesymbol_layout);
//        strength_layout = view.findViewById(R.id.strength_layout);
//        instructions_layout = view.findViewById(R.id.instructions_layout);
//        refilling_layout = view.findViewById(R.id.refilling_layout);
//        number_txt=view.findViewById(R.id.number_txt);
//        timer_txt=view.findViewById(R.id.timer_txt);
//        schedualing_txt=view.findViewById(R.id.schedualing_txt);
//        selectdate=view.findViewById(R.id.selectdate);
    }
    private void initGoneViews(){
        nextlayoutthree.setVisibility(View.GONE);
        nextlayoutfour.setVisibility(View.GONE);
        moreopitionsBtn.setVisibility(View.GONE);
        reminderTime_layout.setVisibility(View.GONE);
        schedualing_layout.setVisibility(View.GONE);
        medicineSymbol_layout.setVisibility(View.GONE);
        strength_layout.setVisibility(View.GONE);
        instructions_layout.setVisibility(View.GONE);
        refilling_layout.setVisibility(View.GONE);
        doneBtn.setVisibility(View.GONE);
    }
    private void handleViewsVisiblity(){
        nextlayoutone.setOnClickListener(view -> {
            nextlayoutthree.setVisibility(View.VISIBLE);
            reminderTime_layout.setVisibility(View.VISIBLE);
            schedualing_layout.setVisibility(View.VISIBLE);
            nextlayoutone.setVisibility(View.GONE);
            doneBtn.setVisibility(View.GONE);
        });
        nextlayoutthree.setOnClickListener(view -> {
            nextlayoutthree.setVisibility(View.GONE);
            nextlayoutfour.setVisibility(View.VISIBLE);
            moreopitionsBtn.setVisibility(View.VISIBLE);
            reminderTime_layout.setVisibility(View.VISIBLE);
            schedualing_layout.setVisibility(View.VISIBLE);
            medicineSymbol_layout.setVisibility(View.VISIBLE);
            doneBtn.setVisibility(View.GONE);
        });
        moreopitionsBtn.setOnClickListener(view -> {
            nextlayoutthree.setVisibility(View.GONE);
            nextlayoutfour.setVisibility(View.GONE);
            moreopitionsBtn.setVisibility(View.GONE);
            reminderTime_layout.setVisibility(View.VISIBLE);
            schedualing_layout.setVisibility(View.VISIBLE);
            medicineSymbol_layout.setVisibility(View.VISIBLE);
            strength_layout.setVisibility(View.VISIBLE);
            instructions_layout.setVisibility(View.VISIBLE);
            refilling_layout.setVisibility(View.VISIBLE);
            doneBtn.setVisibility(View.VISIBLE);
        });
    }
    private void handleClickListener(){
        timer_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimerPickerDialoug();
            }
        });
        number_txt.setOnClickListener(view -> {
            //openNumberPicker();
        });
        schedualing_txt.setOnClickListener(view -> {
            //openDatePicker();
        });
        Presstoadjust_txt.setOnClickListener(view -> {
            //openStrengthDialoug();
        });
    }
    public void openTimerPickerDialoug(){
        TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                timerHour = hourOfDay;
                timerMinute=minute;
                String time=timerHour+":"+timerMinute;
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                try {
                    Date date=simpleDateFormat.parse(time);
                    SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("hh:mm:aa");
                    timer_txt.setText(simpleDateFormat1.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        },12,0,false);
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.updateTime(timerHour,timerMinute);
        timePickerDialog.show();
    }

    @Override
    public void DrugAdded() {
        //to navigate to drug list
    }
//    public void openNumberPicker(){
//        DialougClass dialougClass=new DialougClass();
//        dialougClass.show(getSupportFragmentManager(),"NumberDialoug");
//
//    }
//    public void openStrengthDialoug(){
//        StrengthDialog strengthDialog=new StrengthDialog();
//        strengthDialog.show(getSupportFragmentManager(),"strengthDialoug");
//    }
//    public void openDatePicker(){
//        DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month=month+1;
//                String date=day+"/"+month+"/"+year;
//                selectdate.setText(date);
//            }
//        },'1',2,5);
//        datePickerDialog.show();
//    }
//    @Override
//    public void displayText(String number) {
//        number_txt.setText(number);
//    }
//
//    @Override
//    public void showText(String number) {
//        Presstoadjust_txt.setText(number);
//    }
}