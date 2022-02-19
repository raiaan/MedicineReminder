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
        drugs.add(new Drug("cong","pill","1g","taken"));
        drugs.add(new Drug("cong","pill","1g","taken"));
        drugs.add(new Drug("cong","pill","1g","taken"));
        drugs.add(new Drug("cong","pill","1g","taken"));
        drugs.add(new Drug("cong","pill","1g","taken"));
        drugs.add(new Drug("cong","pill","1g","taken"));
        drugs.add(new Drug("cong","pill","1g","taken"));
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
}
