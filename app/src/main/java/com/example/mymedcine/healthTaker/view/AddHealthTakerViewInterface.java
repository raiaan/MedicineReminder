package com.example.mymedcine.healthTaker.view;

import com.example.mymedcine.model.Drug;

import java.util.ArrayList;
import java.util.List;

public interface AddHealthTakerViewInterface {
    ArrayList<Drug> sendDrugList(List<Drug> drugs);
    ArrayList<String> sendEmailList(List<String> str);
}
