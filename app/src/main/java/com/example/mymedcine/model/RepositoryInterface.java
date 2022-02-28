package com.example.mymedcine.model;

import androidx.lifecycle.LiveData;

import com.example.mymedcine.network.FirebaseConnectionDelegated;

import java.util.ArrayList;
import java.util.List;

public interface RepositoryInterface {
            //NetworkDelegate
    ArrayList<Drug> getAllDrugs();
    void insertDrug(Drug drug);
    void deleteDrug(Drug drug);
    LiveData<List<Drug>> getStoredDrugs();
    LiveData<List<Drug>> getAllDrugsForTheDay(String day);
    void signup(User user, FirebaseConnectionDelegated delegated);
    void login(User user, FirebaseConnectionDelegated delegated);
    void resetPassword(String email, FirebaseConnectionDelegated delegated);
    boolean isUserSignedUp();
    LiveData<Drug> getDrug(String name);
    void updateData(Drug drug);
    LiveData<List<Drug>> getDummyData();

    void logout();
}
