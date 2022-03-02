package com.example.mymedcine.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.mymedcine.database.LocalSourceInterface;
import com.example.mymedcine.database.MedDAO;
import com.example.mymedcine.network.FireBaseConnectionInterface;
import com.example.mymedcine.network.FirebaseConnectionDelegated;
import com.example.mymedcine.utils.worker.OneTimeWorker;
import com.example.mymedcine.utils.worker.PeriodicWorker;

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





    public static Repository getInstance(FireBaseConnectionInterface fireBaseConnection,LocalSourceInterface localSource, Context context) {
        if (repository == null) {
            repository = new Repository(context, localSource,fireBaseConnection);
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
        ArrayList<Long> times = drug.getHours();
        if (times!= null){
            Data data = new Data.Builder()
                    .putLongArray("delayArray", calculateDelay(drug.getHours()))
                    .build();
            OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(OneTimeWorker.class)
                    .addTag(drug.getName())
                    .setInitialDelay(calculateDelay(times)[0], TimeUnit.MILLISECONDS)
                    .setInputData(data)
                    .build();
            WorkManager workManager = WorkManager.getInstance(context.getApplicationContext());
            workManager.enqueue(oneTimeWorkRequest);
        }
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
        System.out.println("data come form database");
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
        fireBaseConnection.resetPassword(email,delegated);
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

    @Override
    public LiveData<List<Drug>> getDummyData() {
        return localSource.getDummyData();
    }

    @Override
    public void logout() {
        fireBaseConnection.logout();
    }

    private long[] calculateDelay(List<Long> times){
        long[] delays = new long[times.size()];
        long currentTime = System.currentTimeMillis();
        // time as milliseconds for date and time
        for(int i = 0; i < times.size(); i ++){
            Date date = new Date(times.get(i));

            Long time = date.getTime();
            Log.i(TAG, "calculateDelay: " + time);
            // devided by 1000 to ignore the date
            delays[i] = time - currentTime ;
            Log.i(TAG, "calculateDelay: " + delays[i]);
        }
        return delays;
    }

    private void setDrugAlarms(long[] delays){
        for(int i = 0; i < delays.length; i ++){
            /*PeriodicWorkRequest drugAlarm = new PeriodicWorkRequest.Builder(MyWorker.class, 1, TimeUnit.DAYS)
                    .addTag(TAG)
                    .setInitialDelay(Duration.ofMillis(delays[i]))
                    .build();

            WorkManager workManager = WorkManager.getInstance(context.getApplicationContext());
            workManager.enqueue(drugAlarm);*/
            Log.i(TAG, "setDrugAlarms: " + new Date(delays[i]).getTime());
        }
    }
}
