package com.example.mymedcine.add_medecine.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import com.example.mymedcine.R;
import com.example.mymedcine.add_medecine.presenter.AddDrugPresenter;
import com.example.mymedcine.add_medecine.presenter.DrugAdder;
import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.model.Repository;
import com.example.mymedcine.utils.IconsFactory;
import com.example.mymedcine.utils.SimpleSpinnerAdapter;
import com.example.mymedcine.utils.ViewDrugConvertor;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Arrays;
import java.util.Random;

public class AddMedecineFragment extends Fragment implements AddMedecineInterface{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Spinner typesSpinner, hoursInDaySpinner,strengthUnitSpinner;
    ArrayAdapter<CharSequence> hoursInDayAdapter,strengthUnitAdapter;
    MaterialButton addToDB;
    SimpleSpinnerAdapter typesAdapter;
    DrugAdder drugAdder;
    ImageView closeBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_medecine, container, false);
        drugAdder = new AddDrugPresenter(this, Repository.getInstance(ConcreteLocalSource.getInstance(getContext())
                , getContext()));
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
        showDatePicker(view);
        toggleRefillContainers(view);
    }

    private void initView(View parentView){
        initTypeSpinner(parentView);
        addToDB = parentView.findViewById(R.id.save_drug);
        addToDB.setOnClickListener(view -> drugAdder.addDrug(ViewDrugConvertor.convertToDrug(parentView)));
        ( (SwitchMaterial)parentView.findViewById(R.id.edit_drug_chronic_disease) )
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
        closeBtn = parentView.findViewById(R.id.add_drug_close_btn);
        closeBtn.setOnClickListener(view -> Navigation.findNavController(parentView).popBackStack());
        initStrengthUnitSpinner(parentView);
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
                if (i ==1) {
                    parentView.findViewById(R.id.hours_picker_2).setVisibility(View.VISIBLE);
                    parentView.findViewById(R.id.hours_picker_3).setVisibility(View.GONE);
                }
                else if (i==2){
                    parentView.findViewById(R.id.hours_picker_3).setVisibility(View.VISIBLE);
                    parentView.findViewById(R.id.hours_picker_2).setVisibility(View.VISIBLE);
                }
                else if(i==0){
                    parentView.findViewById(R.id.hours_picker_2).setVisibility(View.GONE);
                    parentView.findViewById(R.id.hours_picker_3).setVisibility(View.GONE);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void showDatePicker(View parentView){
        parentView.findViewById(R.id.edit_drug_end_date).setOnClickListener(view1 -> {
            parentView.findViewById(R.id.edit_drug_end_date_picker_date).setVisibility(View.VISIBLE);
        });
    }
    private void toggleRefillContainers(View parentView){
        ( (SwitchMaterial)parentView.findViewById(R.id.edit_drug_refill_reminder_switch))
                .setOnCheckedChangeListener((compoundButton, b) -> {
                    if (b){
                        showHideRefillView(parentView , View.VISIBLE);
                    }else{
                        showHideRefillView(parentView , View.GONE);
                    }
                });
    }
    private void showHideRefillView(View view ,int visibiltiy){
        view.findViewById(R.id.edit_drug_current_number).setVisibility(visibiltiy);
        view.findViewById(R.id.edit_drug_refill_reminder_notify).setVisibility(visibiltiy);
        view.findViewById(R.id.edit_drug_current_amount_label).setVisibility(visibiltiy);
        view.findViewById(R.id.edit_drug_refill_reminder_notify_label).setVisibility(visibiltiy);
    }
    private void initStrengthUnitSpinner(View parentView){
        strengthUnitSpinner= parentView.findViewById(R.id.edit_drug_strength_unit);
        strengthUnitAdapter = ArrayAdapter.createFromResource(this.getContext(),R.array.units, android.R.layout.simple_spinner_item);
        strengthUnitSpinner.setAdapter(strengthUnitAdapter);
    }
}