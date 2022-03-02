package com.example.mymedcine.add_dose.presenter;

import com.example.mymedcine.add_dose.views.DoseAdder;
import com.example.mymedcine.add_medecine.view.AddMedecineInterface;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.LastTime;
import com.example.mymedcine.model.RepositoryInterface;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddDosePresenter implements AddDosePresenterInterface{
    RepositoryInterface repo;
    DoseAdder doseAdder;
    public AddDosePresenter(RepositoryInterface repo, DoseAdder doseAdder) {
        this.repo = repo;
        this.doseAdder = doseAdder;
    }

    @Override
    public void addDose(Drug drug, int doseQTY) {
        int remainingItems = drug.left-doseQTY;
        if (drug.left>= 1)
        {
            drug.left = remainingItems;
        }
        else drug.left = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        drug.lastTimeTaken = formatter.format(date);
        drug.lastTimeDoseGiver = "user name";
        repo.updateData(drug);
        doseAdder.updateView(drug);
    }
}
