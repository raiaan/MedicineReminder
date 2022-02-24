package com.example.mymedcine.drugdetails.view;

import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.Prescription;

public interface DrugDisplayer {
    void displayDrugDetails(Drug drug);
    void deleteDrugSuccess();
    void navigateToEditDrug(Drug drug);
}
