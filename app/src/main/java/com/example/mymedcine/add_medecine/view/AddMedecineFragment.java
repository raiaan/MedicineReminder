package com.example.mymedcine.add_medecine.view;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.mymedcine.MainActivity;
import com.example.mymedcine.R;
import com.example.mymedcine.add_medecine.presenter.AddDrugPresenter;
import com.example.mymedcine.add_medecine.presenter.DrugAdder;
import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.drugdetails.presenter.DisplayDrugPresenter;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.LastTime;
import com.example.mymedcine.model.RemindingTimes;
import com.example.mymedcine.model.Repository;
import com.example.mymedcine.network.FireBaseConnection;
import com.example.mymedcine.utils.IconsFactory;
import com.example.mymedcine.utils.SimpleSpinnerAdapter;
import com.example.mymedcine.utils.ViewDrugConvertor;
import com.google.android.material.navigation.NavigationBarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class AddMedecineFragment extends Fragment implements AddMedecineInterface{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Spinner recurrencySpinner, typesSpinner, hoursInDaySpinner;
    ArrayAdapter<CharSequence> recurrencyAdapter ,hoursInDayAdapter;
    Button addToDB;
    SimpleSpinnerAdapter typesAdapter;
    DrugAdder drugAdder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_medecine, container, false);
        drugAdder = new AddDrugPresenter(this, Repository.getInstance(FireBaseConnection.getInstance(),ConcreteLocalSource.getInstance(getContext()), getContext()));
        return view;
    }

    @Override
    public void DrugAdded() {
        Navigation.findNavController(getView()).popBackStack();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View parentView){
        initTypeSpinner(parentView);
        addToDB = parentView.findViewById(R.id.save_drug);
        addToDB.setOnClickListener(view -> drugAdder.addDrug(ViewDrugConvertor.convertToDrug(parentView)));
        ( (Switch)parentView.findViewById(R.id.edit_drug_chronic_disease) )
                .setOnCheckedChangeListener((compoundButton, b) -> {
                    if (b){
                        parentView.findViewById(R.id.edit_drug_end_date).setVisibility(View.GONE);
                        parentView.findViewById(R.id.edit_drug_end_date_label).setVisibility(View.GONE);
                    }else{
                        parentView.findViewById(R.id.edit_drug_end_date).setVisibility(View.VISIBLE);
                        parentView.findViewById(R.id.edit_drug_end_date_label).setVisibility(View.VISIBLE);
                    }
                });
        inithoursInDaySpinner(parentView);
    }

    private void initTypeSpinner(View view){
        typesSpinner = view.findViewById(R.id.edit_drug_types);
        typesAdapter = new SimpleSpinnerAdapter(this.getActivity(),R.layout.custome_types_spinner, Arrays.asList(IconsFactory.getDrugIconsNames()));
        typesSpinner.setAdapter(typesAdapter);
    }

    private void inithoursInDaySpinner(View parentView){
        hoursInDaySpinner = parentView.findViewById(R.id.edit_drug_reminding_hours_per_day);
        hoursInDayAdapter =ArrayAdapter.createFromResource(this.getContext(),R.array.reminding_times_of_hours, android.R.layout.simple_spinner_item);
        hoursInDayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hoursInDaySpinner.setAdapter(hoursInDayAdapter);
        hoursInDaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i ==1)
                    parentView.findViewById(R.id.hours_picker_2).setVisibility(View.VISIBLE);
                if (i==2)
                    parentView.findViewById(R.id.hours_picker_3).setVisibility(View.VISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void voidShowHoursDatePicker(int counter){
       // do {
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                (view, hourOfDay, minute) -> {
                    //txtTime.setText(hourOfDay + ":" + minute);
                }, mHour, mMinute, false);
            timePickerDialog.show();
            counter--;
        //}while (counter <= 0);
    }
}