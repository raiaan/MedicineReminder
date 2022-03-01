package com.example.mymedcine.drugdetails.presenter;

import androidx.lifecycle.LiveData;

import com.example.mymedcine.drugdetails.view.DrugDisplayer;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.RepositoryInterface;

public class DisplayDrugPresenter implements DisplayDrugPresentable{
    DrugDisplayer drugDisplayer;
    RepositoryInterface repository;

    public DisplayDrugPresenter(DrugDisplayer drugDisplayer,RepositoryInterface repository) {
        this.drugDisplayer = drugDisplayer;
        this.repository = repository;
    }

    @Override
    public void deleteDrug(Drug drug) {
        repository.deleteDrug(drug);
        drugDisplayer.deleteDrugSuccess();
    }


    @Override
    public void addDose(Drug drug) {
        repository.updateData(drug);
    }

    @Override
    public void changeState(Drug drug) {
        drug.setState(drug.getState().equals("active")? "suspend":"active");
        repository.updateData(drug);
        drugDisplayer.changeStateCallback(drug);
    }

    @Override
    public void refillDrug(Drug drug) {

    }

    @Override
    public void getDrug(String name) {
        LiveData<Drug> drug =  repository.getDrug(name);
        drugDisplayer.displayDrugDetails(drug.getValue());
    }
}
