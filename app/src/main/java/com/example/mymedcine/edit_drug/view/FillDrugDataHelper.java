package com.example.mymedcine.edit_drug.view;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mymedcine.R;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.utils.SimpleSpinnerAdapter;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FillDrugDataHelper {
    public static void fillData(View parentView, Drug drug){
        Spinner typeSpinner,strengthUnit , hoursofDay;
        ((TextView)parentView.findViewById(R.id.addDrugtoolBarTitle)).setText("Edit Medcine");
        ((TextView) parentView.findViewById(R.id.edit_drug_name)).setText(drug.getName());
        typeSpinner = parentView.findViewById(R.id.edit_drug_types);
        strengthUnit = parentView.findViewById(R.id.edit_drug_strength_unit);
        hoursofDay = parentView.findViewById(R.id.edit_drug_reminding_hours_per_day);
        int strengthPosition = ( (ArrayAdapter)strengthUnit.getAdapter()).getPosition(drug.getStrongUnit());
        strengthUnit.setSelection(strengthPosition);
        int typePosition = ( (SimpleSpinnerAdapter)typeSpinner.getAdapter() ).getPosition(drug.getType());
        typeSpinner.setSelection(typePosition);
        hoursofDay.setSelection(drug.getHours().size());
        ((SwitchMaterial)parentView.findViewById(R.id.edit_drug_chronic_disease)).setChecked(drug.isChoronic);
        ((EditText)parentView.findViewById(R.id.edit_drug_strength_value)).setText(drug.strongValue);
        Date date = new Date(Long.parseLong(drug.endDate));
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        ((EditText)parentView.findViewById(R.id.edit_drug_end_date)).setText(dateFormat.format(date));
        ((SwitchMaterial)parentView.findViewById(R.id.edit_drug_refill_reminder_switch)).setChecked(drug.remindRefill);
        ((EditText)parentView.findViewById(R.id.edit_drug_refill_reminder_notify)).setText(""+drug.getRefillRemindCount());
        ((EditText)parentView.findViewById(R.id.edit_drug_current_number)).setText(""+drug.left);
        if (drug.getReasons() != null)
            ((TextView)parentView.findViewById(R.id.edit_drug_condition)).setText(drug.getReasons());

    }
}
