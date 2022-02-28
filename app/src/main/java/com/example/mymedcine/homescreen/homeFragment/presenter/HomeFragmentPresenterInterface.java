package com.example.mymedcine.homescreen.homeFragment.presenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.example.mymedcine.model.Drug;

import java.util.List;

public interface HomeFragmentPresenterInterface {
    void getAllDrugsOfTheDay(LifecycleOwner lifecycleOwner, String day);
    void getDummyData(LifecycleOwner lifecycleOwner);
}
