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

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.mymedcine.R;
import com.example.mymedcine.homescreen.HomeActivity;
import com.example.mymedcine.utils.IconsFactory;

import java.util.concurrent.TimeUnit;

public class OneTimeWorker extends Worker {

    public OneTimeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        createNotificationChannel();
        /*Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent snoozeIntent = new Intent(this, MyBroadcastReceiver.class);
        snoozeIntent.setAction(ACTION_SNOOZE);
        snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);
*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "app");
        builder.setContentTitle(String.valueOf(R.string.notificationTitle))
                .setContentText(String.valueOf(R.string.notificationText))
               // .setLargeIcon(IconsFactory.getIcon(getApplicationContext(), getInputData().getString(DataUtils.typeKey)))
                .setSmallIcon(R.drawable.ic_pills)
                .setColor(0xFFC06014)
              //  .setSound(alarmSound)
                .setAutoCancel(true)
                .setAutoCancel(true)
                //.setContentIntent(peNotification)

                ;

        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

        //The next work request
        Data output = getInputData();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(OneTimeWorker.class)
                .addTag(output.getString(DataUtils.nameKey))
                .setInitialDelay(24, TimeUnit.HOURS)
                .setInputData(output)
                .build();
        WorkManager workManager = WorkManager.getInstance(getApplicationContext());
        workManager.enqueue(oneTimeWorkRequest);

        return null;
    }


    private void createNotificationChannel() {
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
}
