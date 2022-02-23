package com.example.mymedcine.medication.presenter;

import android.content.Context;

import com.example.mymedcine.medication.view.OnMedecationInterface;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.RepositoryInterface;

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
    public void getMeds() {
        repo.getAllDrugs();
    }

    @Override
    public void addMed(Drug drug) {
        repo.insertDrug(drug);
    }
}
