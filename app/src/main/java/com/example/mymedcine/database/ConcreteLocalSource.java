package com.example.mymedcine.database;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.example.mymedcine.model.Drug;
import java.util.List;

public class ConcreteLocalSource implements LocalSourceInterface{
    MedDAO medDAO;
    static ConcreteLocalSource localSource = null;
    LiveData<List<Drug>> storedDrugs;
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
    public LiveData<Drug> getDrugData(String drugName) {
        return medDAO.getDrugData(drugName);
    }
}
