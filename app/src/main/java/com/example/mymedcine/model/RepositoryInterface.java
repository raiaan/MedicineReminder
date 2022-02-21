package com.example.mymedcine.model;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public interface RepositoryInterface {
            //NetworkDelegate
    ArrayList<Drug> getAllDrugs();
    void insertDrug(Drug drug);
    void deleteDrug(Drug drug);
    LiveData<List<Drug>> getStoredDrugs();
    LiveData<Drug> getDrug(String name);
    void updateData(Drug drug);
}
