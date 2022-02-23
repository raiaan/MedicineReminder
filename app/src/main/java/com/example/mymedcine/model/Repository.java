package com.example.mymedcine.model;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mymedcine.database.AppDataBase;
import com.example.mymedcine.database.LocalSourceInterface;
import com.example.mymedcine.database.MedDAO;
import com.example.mymedcine.network.FireBaseConnection;
import com.example.mymedcine.network.FireBaseConnectionInterface;
import com.example.mymedcine.network.FirebaseConnectionDelegated;

import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryInterface {

    Context context;
    LocalSourceInterface localSource;
    FireBaseConnectionInterface fireBaseConnection;
    private static Repository repository = null;

    private Repository(Context context, LocalSourceInterface localSource, FireBaseConnectionInterface fireBaseConnection) {
        this.context = context;
        this.localSource = localSource;
        this.fireBaseConnection = fireBaseConnection;
    }

    static MedDAO medDAO;

    public Repository(Context context) {
        this.context = context;
   }
/*
    public Repository(Activity activity) {
        AppDataBase medData = AppDataBase.getInstance(activity.getApplicationContext());
        fireBaseConnection = FireBaseConnection.getInstance();
        medDAO = medData.medDao();
    }*/





    public static Repository getInstance(FireBaseConnectionInterface fireBaseConnection,LocalSourceInterface localSource, Context context) {
        if (repository == null) {
            repository = new Repository(context, localSource,fireBaseConnection);
        }
        return repository;
    }


    @Override
    public ArrayList<Drug> getAllDrugs() {
        ArrayList<Drug> drugs = new ArrayList<>();

        drugs.add(new Drug("cong","pill","200","g","taken"));
        drugs.add(new Drug("cong","pill","200","g","taken"));
        drugs.add(new Drug("cong","pill","200","g","taken"));
        drugs.add(new Drug("cong","pill","200","g","taken"));
        drugs.add(new Drug("cong","pill","200","g","taken"));
        drugs.add(new Drug("cong","pill","200","g","taken"));
        drugs.add(new Drug("cong","pill","200","g","taken"));

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
    public LiveData<List<Drug>> getAllDrugsForTheDay(String day) {
        return localSource.getAllDrugsOfTheDay(day);
    }

    @Override
    public void signup(User user, FirebaseConnectionDelegated delegated) {
        fireBaseConnection.signup(user, delegated);
    }

    @Override
    public void login(User user, FirebaseConnectionDelegated delegated) {
        fireBaseConnection.login(user, delegated);
    }

    @Override
    public boolean resetPassword(String email, FirebaseConnectionDelegated delegated) {
        return fireBaseConnection.resetPassword(email,delegated);
    }

    @Override
    public boolean isUserSignedUp() {
        return fireBaseConnection.isUserSignIn();
    }

    public LiveData<Drug> getDrug(String name) {
        return localSource.getDrugData(name);
    }

    @Override
    public void updateData(Drug drug) {
        localSource.update(drug);
    }
}
