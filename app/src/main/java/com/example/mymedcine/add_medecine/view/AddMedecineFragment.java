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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddMedecineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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
    private String mParam1;
    private String mParam2;

    public AddMedecineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMedecineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMedecineFragment newInstance(String param1, String param2) {
        AddMedecineFragment fragment = new AddMedecineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_medecine, container, false);
        return view;
    }
    private void initUI(View view){

    }
    private void initGoneViews(){

    }
    private void handleViewsVisiblity(){
    }
    private void handleClickListener(){
    }
    public void openTimerPickerDialoug(){

    }

    @Override
    public void DrugAdded() {
        //to navigate to drug list
    }

}