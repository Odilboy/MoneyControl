package com.example.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

import com.example.moneycontrol.MainActivity;
import com.example.moneycontrol.R;


public class Tab1Service extends IntentService {

    private static final int NOTIFICATION_ID = 3;


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public Tab1Service() {

        super("Tab1Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show();
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("My Title");
        builder.setContentText("This is the Body");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);

    }
}
