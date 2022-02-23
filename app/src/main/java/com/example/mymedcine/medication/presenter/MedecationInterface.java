package com.example.mymedcine.medication.presenter;

import androidx.lifecycle.LifecycleOwner;

import com.example.mymedcine.model.Drug;

public interface MedecationInterface {
    void getMedsToSendIt(LifecycleOwner owner);
    void addMedToSendIt(Drug drug);

}
