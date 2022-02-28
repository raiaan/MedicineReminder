package com.example.mymedcine.healthTaker.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.mymedcine.healthTaker.view.AddHealthTakerViewInterface;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.RepositoryInterface;

import java.util.List;

public class HealthTakerPresenter implements HealthTakerInterface {

    RepositoryInterface repo;
    AddHealthTakerViewInterface view;

    public HealthTakerPresenter(RepositoryInterface repo, AddHealthTakerViewInterface view) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    public void drugList(LifecycleOwner owner) {

        repo.getStoredDrugs().observe(owner, new Observer<List<Drug>>() {
            @Override
            public void onChanged(List<Drug> drugs) {
                view.sendDrugList(drugs);
            }
        });
    }

    @Override
    public void emailList(LifecycleOwner owner) {
        repo.getAllEmails().observe(owner, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                view.sendEmailList(strings);
            }
        });
    }
}
