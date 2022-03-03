package com.example.mymedcine.notification_dialoug.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.mymedcine.R;
import com.example.mymedcine.model.Drug;
import com.google.android.material.button.MaterialButton;

public class NotificationDialog extends AlertDialog implements
        android.view.View.OnClickListener{
    public NotificationDialog(@NonNull Context context , Drug drug , String Time) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifacation_layout);

    }

    @Override
    public void onClick(View view) {

    }
}
