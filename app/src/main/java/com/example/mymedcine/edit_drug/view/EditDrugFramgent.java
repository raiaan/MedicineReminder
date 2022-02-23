package com.example.mymedcine.edit_drug.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.mymedcine.R;
import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.edit_drug.presenter.UpdateDrugPresenter;
import com.example.mymedcine.edit_drug.presenter.UpdateDrugPresenterInterface;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.Repository;

public class EditDrugFramgent extends Fragment implements EditDrugInterface{


    private UpdateDrugPresenterInterface presenterInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_drug_framgent, container, false);
        presenterInterface = new UpdateDrugPresenter(this, Repository.getInstance(ConcreteLocalSource.getInstance(getContext())
                , getContext()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void updateView(Drug drug) {

    }
}