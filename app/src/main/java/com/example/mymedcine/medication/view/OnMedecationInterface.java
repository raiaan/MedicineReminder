package com.example.mymedcine.medication.view;

import android.view.View;

import com.example.mymedcine.model.Drug;

import java.util.List;

public interface OnMedecationInterface {
    void showData(List<Drug> drugs);
    void onMedecineClickListener(View view, int position);
    void addMed(Drug drug);
}
