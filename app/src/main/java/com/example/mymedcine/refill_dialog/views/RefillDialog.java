package com.example.mymedcine.refill_dialog.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.mymedcine.R;
import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.drugdetails.presenter.DisplayDrugPresentable;
import com.example.mymedcine.drugdetails.view.DrugDisplayer;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.Repository;
import com.example.mymedcine.network.FireBaseConnection;
import com.example.mymedcine.refill_dialog.presenter.RefillPresenter;
import com.example.mymedcine.refill_dialog.presenter.RefillPresenterInterface;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class RefillDialog extends Dialog implements
        android.view.View.OnClickListener , RefillDialogInterface{
    Drug drug;
    DrugDisplayer drugDisplayer;
    RefillPresenterInterface refillPresenter;
    public RefillDialog(@NonNull Context context, Drug drug , DrugDisplayer drugDisplayer) {
        super(context);
        this.drug = drug;
        this.drugDisplayer = drugDisplayer;
        refillPresenter = new RefillPresenter(this,Repository.getInstance(
                                    FireBaseConnection.getInstance(), ConcreteLocalSource.getInstance(context),context));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.refill_dialog);
        ( (ImageView)findViewById(R.id.increment_drug_qty) ).setOnClickListener(this);
        ( (ImageView)findViewById(R.id.decrement_drug_qty) ).setOnClickListener(this);
        updateEditText(drug.left);
        ( (Button)findViewById(R.id.refill_dialog_cancel_btn) ).setOnClickListener(this);
        ( (MaterialButton)findViewById(R.id.confirm_refill) ).setOnClickListener(this);

    }
    private void updateEditText(int number){
        (  (EditText)findViewById(R.id.drug_refill_amount)).setText(""+number);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.increment_drug_qty:
                drug.left++;
                updateEditText(drug.left);
                break;
            case R.id.decrement_drug_qty:
                drug.left--;
                updateEditText(drug.left);
                break;
            case R.id.refill_dialog_cancel_btn:
                dismiss();
                break;
            case R.id.confirm_refill:
                refillPresenter.changeQTY(drug);
                dismiss();
                break;
        }
    }

    @Override
    public void updateView(Drug drug) {
        drugDisplayer.changeQTYcallback(drug.getLeft(),drug.getRefillRemindCount());
    }
}
