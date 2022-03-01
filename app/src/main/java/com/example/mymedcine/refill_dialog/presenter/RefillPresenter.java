package com.example.mymedcine.refill_dialog.presenter;

import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.RepositoryInterface;
import com.example.mymedcine.refill_dialog.views.RefillDialogInterface;

public class RefillPresenter implements RefillPresenterInterface{
    RepositoryInterface repository;
    RefillDialogInterface qtyDialog;
    public RefillPresenter(RefillDialogInterface qtyDialog,RepositoryInterface repository) {
        this.repository = repository;
        this.qtyDialog = qtyDialog;
    }
    @Override
    public void changeQTY(Drug drug) {
        repository.updateData(drug);
        qtyDialog.updateView(drug);
    }
}
