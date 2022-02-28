package com.example.mymedcine.healthTaker.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mymedcine.R;
import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.healthTaker.DAOFirebase;
import com.example.mymedcine.healthTaker.presenter.HealthTakerInterface;
import com.example.mymedcine.healthTaker.presenter.HealthTakerPresenter;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.Prescription;
import com.example.mymedcine.model.Repository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AddHealthTakerFragment extends Fragment implements AddHealthTakerViewInterface {

    TextInputEditText editText;
    MaterialButton btnSend;

    HealthTakerInterface presenter;
    ArrayList<Drug> drugList ;
    ArrayList<String> emailList ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_health_taker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.edtEmail);
        btnSend = view.findViewById(R.id.btnSendInvit);

        presenter = new HealthTakerPresenter( Repository.getInstance(ConcreteLocalSource.getInstance(getContext()),getContext()),this );
        presenter.drugList(this);

        btnSend.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String edtEmail = editText.getText().toString().trim();

                DAOFirebase daoFirebase = new DAOFirebase();

                ArrayList<String> emailList = new ArrayList<>();
                emailList.add("1@gmail.com");
                emailList.add("2@gmail.com");
                emailList.add("3@gmail.com");
                Prescription healthTaker = new Prescription( edtEmail,"hani", emailList, drugList);

                daoFirebase.addHealthTaker(healthTaker).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "The invitation has arrived", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getContext(), "There is an error!!!", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
    }

    @Override
    public ArrayList<Drug> sendDrugList(List<Drug> drugs) {
        drugList = (ArrayList<Drug>) drugs;

        return drugList;
    }

    @Override
    public ArrayList<String> sendEmailList(List<String> emails) {
        emailList = (ArrayList<String>) emails;

        return emailList;
    }
}