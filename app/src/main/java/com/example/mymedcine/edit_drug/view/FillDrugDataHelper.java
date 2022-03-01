package com.example.mymedcine.edit_drug.view;

import android.view.View;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.mymedcine.R;
import com.example.mymedcine.model.Drug;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class FillDrugDataHelper {
    public static void fillData(View parentView, Drug drug){
        ((TextView) parentView.findViewById(R.id.edit_drug_name)).setText(drug.getName());
        ((Spinner) parentView.findViewById(R.id.edit_drug_types)).setSelection(3);
        ((SwitchMaterial)parentView.findViewById(R.id.edit_drug_chronic_disease)).setChecked(drug.isChoronic);
        if (drug.getReasons() != null)
            ((TextView)parentView.findViewById(R.id.edit_drug_condition)).setText(drug.getReasons());
    }
}
