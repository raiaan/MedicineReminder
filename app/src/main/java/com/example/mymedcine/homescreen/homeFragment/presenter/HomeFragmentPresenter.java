package com.example.mymedcine.homescreen.homeFragment.presenter;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.homescreen.homeFragment.view.HomeFragmentViewInterface;
import com.example.mymedcine.model.Drug;

import java.util.ArrayList;
import java.util.List;

import com.example.mymedcine.model.Repository;
import com.example.mymedcine.model.RepositoryInterface;

public class HomeFragmentPresenter implements HomeFragmentPresenterInterface{
    Context context;
    RepositoryInterface repository;
    HomeFragmentViewInterface view;


    public HomeFragmentPresenter(Context context, RepositoryInterface repository, HomeFragmentViewInterface view) {
        this.context = context;
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void getAllDrugsOfTheDay(LifecycleOwner lifecycleOwner) {
        System.out.println("data is on presenter now");
        LiveData dailyDrugs = repository.getAllDrugsForTheDay();
        dailyDrugs.observe(lifecycleOwner, new Observer<List<Drug>>() {
            @Override
            public void onChanged(List<Drug> drugs) {
                System.out.println("data is " + drugs.size() + " rows");
                view.desplayDrugs(drugs);
                }
            }
        );
    }
}
