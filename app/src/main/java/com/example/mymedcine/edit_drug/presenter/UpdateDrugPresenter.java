package com.example.mymedcine.edit_drug.presenter;

import com.example.mymedcine.drugdetails.view.DrugDisplayer;
import com.example.mymedcine.edit_drug.view.EditDrugInterface;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.RepositoryInterface;

public class UpdateDrugPresenter implements UpdateDrugPresenterInterface{
    RepositoryInterface repository;
    EditDrugInterface editDrug;
    public UpdateDrugPresenter(EditDrugInterface editDrug,RepositoryInterface repository) {
        this.repository = repository;
        this.editDrug = editDrug;
    }
    @Override
    public void updateDrug(Drug drug) {
        editDrug.updateView(drug);
        repository.updateData(drug);
    }

    @Override
    public void getDrug(String drug) {
        Drug drugResult = repository.getDrug(drug).getValue();
        editDrug.updateView(drugResult);
    }
}
