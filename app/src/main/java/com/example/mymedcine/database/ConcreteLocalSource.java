package com.example.mymedcine.database;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import com.example.mymedcine.model.Drug;
import java.util.List;

public class ConcreteLocalSource implements LocalSourceInterface{
    MedDAO medDAO;
    String TAG = "TAG";
    static ConcreteLocalSource localSource = null;
    LiveData<List<Drug>> storedDrugs;

    LiveData<List<Drug>> dailyDrugs;
    String myDay;
    private ConcreteLocalSource(Context context) {
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        this.medDAO = db.medDao();
        this.storedDrugs = medDAO.getAllDrugs();
    }
    public static ConcreteLocalSource getInstance(Context context){
        if (localSource == null){
            localSource = new ConcreteLocalSource(context);
        }
        return localSource;
    }
    @Override
    public void insert(Drug drug) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                medDAO.add(drug);
            }
        }).start();

    }

    @Override
    public void delete(Drug drug) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                medDAO.delete(drug);
            }
        }).start();
    }
    @Override
    public void update(Drug drug) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                medDAO.update(drug);
            }
        }).start();
    }
    @Override
    public LiveData<List<Drug>> getAllStoredDrugs() {
        return storedDrugs;
    }
    @Override
    public LiveData<List<Drug>> getAllDrugsOfTheDay(String day) {
        Log.i(TAG, "getDummyData:  we are in repo getting the daily drugs");
        return medDAO.getAllDrugsOfTheDay(day);
    }

    public LiveData<Drug> getDrugData(String drugName) {
        return medDAO.getDrugData(drugName);
    }

    @Override
    public LiveData<List<Drug>> getDummyData() {
        return medDAO.getDummyData();
    }

}
