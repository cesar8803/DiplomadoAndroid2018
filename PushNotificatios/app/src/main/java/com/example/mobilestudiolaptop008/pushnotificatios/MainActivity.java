package com.example.mobilestudiolaptop008.pushnotificatios;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   /*     Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        Uri defaultSoungUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        Notification.Builder notificationbuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_car)
                .setContentTitle("Hola")
                .setContentText("Hola mundo")
                .setSound(defaultSoungUri)
                .setContentIntent(pendingIntent);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String chanelId = getString(R.string.normal_chanel_id);
            String chanelName = getString(R.string.normal_chanel_name);

            NotificationChannel channel = new NotificationChannel(chanelId,chanelName,NotificationManager.IMPORTANCE_DEFAULT);

            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100,200,200,50});

            if (notificationManager != null)
            {

                notificationManager.createNotificationChannel(channel);

            }
            notificationbuilder.setChannelId(chanelId);
        }


        if(notificationManager != null)
        {

            notificationManager.notify("",0,notificationbuilder.build());

        }


        if(FirebaseInstanceId.getInstance().getToken() != null)
        {

            Log.d("++++++++++++",FirebaseInstanceId.getInstance().getToken());

        }*/

    }
}
