package com.example.mymedcine.homescreen.homeFragment.presenter;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.homescreen.homeFragment.view.HomeFragmentViewInterface;
import com.example.mymedcine.model.Drug;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.mymedcine.model.DrugsBerDay;
import com.example.mymedcine.model.Repository;
import com.example.mymedcine.model.RepositoryInterface;



public class HomeFragmentPresenter implements HomeFragmentPresenterInterface{
    Context context;
    RepositoryInterface repository;
    HomeFragmentViewInterface view;

    final String TAG = "TAG";


    public HomeFragmentPresenter(Context context, RepositoryInterface repository, HomeFragmentViewInterface view) {
        this.context = context;
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void getAllDrugsOfTheDay(LifecycleOwner lifecycleOwner , String day) {
        LiveData dailyDrugs = repository.getAllDrugsForTheDay(day);
        dailyDrugs.observe(lifecycleOwner, new Observer<List<Drug>>() {
            @Override
            public void onChanged(List<Drug> drugs) {
                List<DrugsBerDay> items = convertDrugsIntoDrugsBerDay(drugs);
                view.desplayDrugs(items);
                }
            }
        );
    }

    @Override
    public void getDummyData(LifecycleOwner lifecycleOwner) {
     /* System.out.println("data is on presenter now");
        LiveData dailyDrugs = repository.getDummyData();
        dailyDrugs.observe(lifecycleOwner, new Observer<List<Drug>>() {
                    @Override
                    public void onChanged(List<Drug> drugs) {
                        Log.i(TAG, "get dummy data on presenter: " +  drugs.size() );
                        view.desplayDrugs(drugs);
                    }
                }
        );*/
    }



    private List<DrugsBerDay> convertDrugsIntoDrugsBerDay(List<Drug> dailyDrugs) {

        LinkedHashMap<String, List<Drug>> sortedDrugs = new LinkedHashMap<>();
        for (Drug drug : dailyDrugs){
            for(String time : drug.getHoursAsTimes()){
                sortedDrugs.put(time ,new ArrayList<>());
            }
        }

        for (Drug drug : dailyDrugs){
            for(String time : drug.getHoursAsTimes()){
                sortedDrugs.get(time).add(drug);
            }
        }
        List<DrugsBerDay> items =new ArrayList<>();
        for( Map.Entry<String, List<Drug>> entry : sortedDrugs.entrySet()){
            items.add(new DrugsBerDay(entry.getKey(),entry.getValue()));
        }
        return items;

    }
}
