package com.example.mymedcine.homescreen.homeFragment.view;

import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.DrugsBerDay;

import java.util.List;

public interface HomeFragmentViewInterface {
    void desplayDrugs(List<DrugsBerDay> items);
    void desplayDummyData(List<Drug> drugs);

}
