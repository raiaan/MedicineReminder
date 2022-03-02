package com.example.mymedcine.utils.worker;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class OneTimeWorker extends Worker {

    public OneTimeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
        r.play();

        return null;
    }
}
