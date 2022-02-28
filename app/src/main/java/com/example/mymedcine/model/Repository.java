package com.example.mymedcine.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mymedcine.database.AppDataBase;
import com.example.mymedcine.database.LocalSourceInterface;
import com.example.mymedcine.database.MedDAO;

import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryInterface {

    LocalSourceInterface localSource;
    private static Repository repository = null;
    private Context context;
    static MedDAO medDAO;

    public Repository(Context context) {
        this.context = context;
        AppDataBase medData = AppDataBase.getInstance(context.getApplicationContext());
        medDAO = medData.medDao();
    }

    private Repository(LocalSourceInterface localSource, Context context) {
        this.localSource = localSource;
        this.context = context;
    }

    public static Repository getInstance(LocalSourceInterface localSource, Context context) {
        if (repository == null) {
            repository = new Repository(localSource, context);

        }
        return repository;
    }


    @Override
    public ArrayList<Drug> getAllDrugs() {
        ArrayList<Drug> drugs = new ArrayList<>();
        return drugs;
    }

    @Override
    public void insertDrug(Drug drug) {
        localSource.insert(drug);
    }

    @Override
    public void deleteDrug(Drug drug) {
        localSource.delete(drug);
    }

    @Override
    public LiveData<List<Drug>> getStoredDrugs() {
        return localSource.getAllStoredDrugs();
    }

    @Override
    public LiveData<Drug> getDrug(String name) {
        return localSource.getDrugData(name);
    }

    @Override
    public LiveData<List<String>> getAllEmails() {
        return localSource.getAllEmails();
    }

    @Override
    public void updateData(Drug drug) {
        localSource.update(drug);
    }
}
