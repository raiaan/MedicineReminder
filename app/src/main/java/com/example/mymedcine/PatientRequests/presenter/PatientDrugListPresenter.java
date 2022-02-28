package com.example.mymedcine.PatientRequests.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.mymedcine.PatientRequests.view.PatientDrugsViewInterface;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.RepositoryInterface;

import java.util.List;

public class PatientDrugListPresenter implements PatientDrugListInterface{

    RepositoryInterface repo;
    PatientDrugsViewInterface view;


}
