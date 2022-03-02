package com.example.mymedcine.utils.worker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PeriodicWorker extends Worker {
    final String TAG = "TAG";

    public PeriodicWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        long[] delays = getInputData().getLongArray("delayArray");
        if(delays != null){
            for(int i = 0; i < delays.length; i ++) {
                OneTimeWorkRequest oneTime = new OneTimeWorkRequest.Builder(OneTimeWorker.class)
                        .setInitialDelay(delays[i], TimeUnit.MILLISECONDS)
                        .build();
                WorkManager workManager = WorkManager.getInstance(getApplicationContext());
                workManager.enqueue(oneTime);
                Log.i(TAG, "doWork: an alarm will start in " + delays[i]);
                Log.i(TAG, "doWork: an alarm will start in " + Duration.ofHours(delays[i]) + "hours");
                Log.i(TAG, "doWork: an alarm will start in " + Duration.ofMillis(delays[i]) + "millis");
                Log.i(TAG, "doWork: an alarm will start in " + Duration.ofMinutes(delays[i]) + "minutes");
            }
        }
        return null;
    }
}
