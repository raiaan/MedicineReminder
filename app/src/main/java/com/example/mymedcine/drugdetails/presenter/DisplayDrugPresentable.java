package com.example.mymedcine.drugdetails.presenter;

import androidx.lifecycle.LiveData;

import com.example.mymedcine.model.Drug;

public interface DisplayDrugPresentable {
    void deleteDrug(Drug drug);
    void addDose(Drug drug);
    void changeState(Drug drug);
    void refillDrug(Drug drug);
    void getDrug(String name);
}
