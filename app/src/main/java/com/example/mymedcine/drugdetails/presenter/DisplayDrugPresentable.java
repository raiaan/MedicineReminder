package com.example.mymedcine.drugdetails.presenter;

import com.example.mymedcine.model.Drug;

public interface DisplayDrugPresentable {
    void deleteDrug(Drug drug);
    Drug editDrug(Drug drug);
    Drug addDose(Drug drug);
    void changeState(Drug drug);
    void refillDrug(Drug drug);
}
