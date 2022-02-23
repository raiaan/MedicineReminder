package com.example.mymedcine.add_medecine.presenter;

import com.example.mymedcine.add_medecine.view.AddMedecineInterface;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.RepositoryInterface;

public class AddDrugPresenter implements DrugAdder{
    RepositoryInterface repo;
    AddMedecineInterface addMedecine;

    public AddDrugPresenter(AddMedecineInterface addMedecine ,RepositoryInterface repo) {
        this.repo = repo;
        this.addMedecine = addMedecine;
    }

    @Override
    public void addDrug(Drug drug) {
        repo.insertDrug(drug);
        addMedecine.DrugAdded();
    }
}
