package com.example.mymedcine.utils.worker;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.mymedcine.R;
import com.example.mymedcine.homescreen.HomeActivity;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.utils.IconsFactory;

import java.util.concurrent.TimeUnit;

public class OneTimeWorker extends Worker {

    public OneTimeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.i("TAG", "doWork: " );
        if (getInputData().getBoolean(DataUtils.refillFlag, false)){
            sendRefillNotification();
        }else{
            sendRemindingNotification();
            Data data = getInputData();
            OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(OneTimeWorker.class)
                    .addTag(data.getString(DataUtils.nameKey))
                    .setInitialDelay(24, TimeUnit.HOURS)
                    .setInputData(data)
                    .build();
        }
        return null;
    }

    private void createNotificationChannel() {
        Log.i("TAG", "createNotificationChannel: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String id = String.valueOf(R.string.notificationChannelID);
            String description = String.valueOf(R.string.notificationChannelDescription);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(id, description, importance);
            /*Register the channel with the system; you can't change the importance
             or other notification behaviors after this*/
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void sendRemindingNotification() {

        createNotificationChannel();

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0,intent,0);
        //1
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),"CHANNEL_ID");
        builder.setContentTitle("Medicine Time")
                .setContentText("It\'s time to take " + getInputData().getString(DataUtils.nameKey))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pi)
                .setSmallIcon(IconsFactory.getIconID(getInputData().getString(DataUtils.typeKey)));

        NotificationManagerCompat nmc = NotificationManagerCompat.from(getApplicationContext());
        nmc.notify(1,builder.build());
    }

    private void sendRefillNotification() {

        //here if the sdk version is >=26 I need Notification channel ... else I don't
        createNotificationChannel();

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0,intent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),"CHANNEL_ID");
        builder.setContentTitle("refill this drug")
                .setContentText(getInputData().getString(DataUtils.nameKey))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pi)
                .setSmallIcon(IconsFactory.getIconID(getInputData().getString(DataUtils.typeKey)));

        NotificationManagerCompat nmc = NotificationManagerCompat.from(getApplicationContext());
        nmc.notify(1,builder.build());
    }

    }

