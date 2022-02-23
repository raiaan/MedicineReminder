package com.example.mymedcine.medication.presenter;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.mymedcine.medication.view.OnMedecationInterface;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.RepositoryInterface;

import java.util.List;

public class MedecationPresenter implements MedecationInterface{
      Context context;
    RepositoryInterface repo;

    OnMedecationInterface view;

    public MedecationPresenter(Context context, RepositoryInterface repo, OnMedecationInterface view) {
        this.context = context;
        this.repo = repo;
        this.view = view;
    }

    @Override
    public void getMedsToSendIt(LifecycleOwner owner) {
        repo.getStoredDrugs().observe(owner, new Observer<List<Drug>>() {
            @Override
            public void onChanged(List<Drug> drugs) {
                view.showData(drugs);
            }
        });
    }

    @Override
    public void addMedToSendIt(Drug drug) {
        repo.insertDrug(drug);
    }
}
