package com.example.mymedcine.database;

import androidx.lifecycle.LiveData;

import com.example.mymedcine.model.Drug;

import java.util.List;

public interface LocalSourceInterface {
    void insert(Drug drug);
    void  delete(Drug drug);
    void  update(Drug drug);
    LiveData<List<Drug>> getAllStoredDrugs();
    LiveData<List<Drug>> getAllDrugsOfTheDay(String day);
    LiveData<Drug> getDrugData(String drugName);
    LiveData<List<Drug>> getDummyData();
}
