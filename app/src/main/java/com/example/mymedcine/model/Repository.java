package com.example.mymedcine.model;

import static com.google.gson.internal.bind.util.ISO8601Utils.format;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.view.textservice.TextInfo;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.mymedcine.database.LocalSourceInterface;
import com.example.mymedcine.database.MedDAO;
import com.example.mymedcine.network.FireBaseConnectionInterface;
import com.example.mymedcine.network.FirebaseConnectionDelegated;
import com.example.mymedcine.utils.worker.DataUtils;
import com.example.mymedcine.utils.worker.OneTimeWorker;
import com.example.mymedcine.utils.worker.PeriodicWorker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Repository implements RepositoryInterface {

    String TAG = "TAG";
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


    public static Repository getInstance(FireBaseConnectionInterface fireBaseConnection, LocalSourceInterface localSource, Context context) {
        if (repository == null) {
            repository = new Repository(context, localSource, fireBaseConnection);
        }
        return repository;
    }


    @Override
    public ArrayList<Drug> getAllDrugs() {
        ArrayList<Drug> drugs = new ArrayList<>();

//        drugs.add(new Drug("cong","pill","200","g","taken"));
//        drugs.add(new Drug("cong","pill","200","g","taken"));
//        drugs.add(new Drug("cong","pill","200","g","taken"));
//        drugs.add(new Drug("cong","pill","200","g","taken"));
//        drugs.add(new Drug("cong","pill","200","g","taken"));
//        drugs.add(new Drug("cong","pill","200","g","taken"));
//        drugs.add(new Drug("cong","pill","200","g","taken"));

        return drugs;
    }

    @Override
    public void insertDrug(Drug drug) {
        createWorkRequest(drug);
        localSource.insert(drug);
    }

    @Override
    public void deleteDrug(Drug drug) {
        WorkManager workManager = WorkManager.getInstance(context.getApplicationContext());
        workManager.cancelAllWorkByTag(drug.getName());
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
    public void resetPassword(String email, FirebaseConnectionDelegated delegated) {
        fireBaseConnection.resetPassword(email, delegated);
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
        WorkManager workManager = WorkManager.getInstance(context.getApplicationContext());
        workManager.cancelAllWorkByTag(drug.getName());
        createWorkRequest(drug);
        createRefillWorker(drug);
        localSource.update(drug);
    }

    @Override
    public LiveData<List<Drug>> getDummyData() {
        return localSource.getDummyData();
    }

    @Override
    public void logout() {
        fireBaseConnection.logout();
    }

    private long[] calculateDelay(List<Long> times) {
        long[] delays = new long[times.size()];
        long currentTime = System.currentTimeMillis();
        Log.i(TAG, "calculateDelay:  system time" + currentTime);
        for (int i = 0; i < times.size(); i++) {
            Log.i(TAG, "calculateDelay: time from db " + times.get(i));
            delays[i] = times.get(i) - currentTime;
            Log.i(TAG, "calculateDelay: delay " + delays[i]);
        }
        return delays;
    }

    public void createWorkRequest(Drug drug) {
        if (drug.getHours() != null) {
            long[] delays = calculateDelay(drug.getHours());
            for (int i = 0; i < delays.length; i++) {
                Data data = new Data.Builder()
                        //   .putLong(DataUtils.delayKey, delays[i])
                        .putString(DataUtils.nameKey, drug.getName())
                        .putString(DataUtils.typeKey, drug.getType())
                        .putBoolean(DataUtils.refillFlag, false)
                        .build();
                OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(OneTimeWorker.class)
                        .addTag(drug.getName())
                        .setInitialDelay(delays[i], TimeUnit.MILLISECONDS)
                        .setInputData(data)
                        .build();
                WorkManager workManager = WorkManager.getInstance(context.getApplicationContext());
                workManager.enqueue(oneTimeWorkRequest);
            }
        }
    }

    public void createRefillWorker(Drug drug) {
        if (drug.getRemindRefill()) {

            if (drug.getRefillRemindCount() <= drug.getLeft()) {
                Data data = new Data.Builder()
                        .putBoolean(DataUtils.refillFlag, true)
                        .putString(DataUtils.typeKey, drug.getType())
                        .putString(DataUtils.nameKey, drug.getName())
                        .build();
                OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(OneTimeWorker.class)
                        .addTag(drug.getName())
                        .setInitialDelay(2000, TimeUnit.MILLISECONDS)
                        .setInputData(data)
                        .build();
                WorkManager workManager = WorkManager.getInstance(context.getApplicationContext());
                workManager.enqueue(oneTimeWorkRequest);

            }
        }
    }
}