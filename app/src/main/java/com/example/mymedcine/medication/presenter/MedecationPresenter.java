package com.example.mymedcine.medication.presenter;

import com.example.mymedcine.medication.view.OnMedecationInterface;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.RepositoryInterface;

public class MedecationPresenter implements MedecationInterface{

    OnMedecationInterface view;
    RepositoryInterface repo;

    public MedecationPresenter(OnMedecationInterface view, RepositoryInterface repo) {
        this.view = view;
        this.repo = repo;
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
