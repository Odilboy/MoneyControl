package com.example.BroadcastReceivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.moneycontrol.MainActivity;
import com.example.moneycontrol.R;
import com.example.services.Tab1Service;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Working", Toast.LENGTH_SHORT).show();


            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent repeatingIntent = new Intent(context, MainActivity.class);
            repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(android.R.drawable.arrow_up_float)
                    .setContentTitle("Title")
                    .setContentText("Text")
                    .setAutoCancel(true);
            notificationManager.notify(100, builder.build());


//        Notification.Builder builder = new Notification.Builder(context);
//        builder.setContentTitle("My Title");
//        builder.setContentText("This is the Body");
//        builder.setSmallIcon(R.drawable.ic_launcher_background);
//        Intent notifyIntent = new Intent(context, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        //to be able to launch your activity from the notification
//        builder.setContentIntent(pendingIntent);
//        Notification notificationCompat = builder.build();
//        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
//        managerCompat.notify(100, notificationCompat);
    }
}
