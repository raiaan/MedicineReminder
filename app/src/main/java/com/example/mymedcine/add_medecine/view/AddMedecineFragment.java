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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_medecine, container, false);

        return view;
    }

    @Override
    public void DrugAdded() {
        //to navigate to drug list
    }

}