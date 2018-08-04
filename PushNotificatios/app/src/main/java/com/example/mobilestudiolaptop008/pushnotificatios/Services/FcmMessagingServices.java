package com.example.mobilestudiolaptop008.pushnotificatios.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;

import com.example.mobilestudiolaptop008.pushnotificatios.MainActivity;
import com.example.mobilestudiolaptop008.pushnotificatios.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FcmMessagingServices extends FirebaseMessagingService {



    private static final String DESCUENTO =  "DISCOUNT";

    public FcmMessagingServices() {

    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        //if(remoteMessage.getData().size()>0 && remoteMessage.getNotification() != null)
        if(remoteMessage.getNotification() != null)
        {

            SendNotification(remoteMessage);


        }

    }

    private void SendNotification(RemoteMessage remoteMessage) {


        float discount = Float.valueOf(remoteMessage.getData().get(DESCUENTO));



        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Uri defaultSoung = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification.Builder notificationbuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_car)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(defaultSoung)
                .setContentIntent(pendingIntent);


        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP) {
            notificationbuilder.setColor(discount > .47 ? ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary) : ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        }

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


    }
}
