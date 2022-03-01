package com.example.mymedcine.add_dose.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.mymedcine.R;
import com.example.mymedcine.model.Drug;
import com.google.android.material.button.MaterialButton;

public class AddDoseDialog extends Dialog implements
        android.view.View.OnClickListener {
    Drug drug;
    public AddDoseDialog(@NonNull Context context ,Drug drug) {
        super(context);
        this.drug = drug;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_dose_dialogue);
        ( (ImageView)findViewById(R.id.increment_drug_dose_qty) ).setOnClickListener(this);
        ( (ImageView)findViewById(R.id.decrement_drug_dose_qty) ).setOnClickListener(this);
        ( (Button)findViewById(R.id.add_dose_dialog_cancel_btn) ).setOnClickListener(this);
        ( (MaterialButton)findViewById(R.id.confirm_add_dose) ).setOnClickListener(this);
    }
    private void updateEditText(int number){
        Integer currentNumber = Integer.parseInt( ( (EditText)findViewById(R.id.drug_dose_amount)).getText().toString());
        (  (EditText)findViewById(R.id.drug_dose_amount)).setText(""+(currentNumber+number));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.increment_drug_dose_qty:
                updateEditText(1);
                break;
            case R.id.decrement_drug_dose_qty:
                if ( Integer.parseInt( ( (EditText)findViewById(R.id.drug_dose_amount)).getText().toString()) < 1){
                    ((ImageView)findViewById(view.getId())).setColorFilter(ContextCompat.getColor(getContext(), R.color.dark_gray), android.graphics.PorterDuff.Mode.MULTIPLY);
                }else updateEditText(-1);
                break;
            case R.id.refill_dialog_cancel_btn:
                dismiss();
                break;
            case R.id.confirm_refill:
                //refillPresenter.changeQTY(drug);
                dismiss();
                break;
        }
    }
}
