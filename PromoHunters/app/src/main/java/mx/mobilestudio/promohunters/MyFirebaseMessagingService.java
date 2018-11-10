package mx.mobilestudio.promohunters;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        // Revisamos si el mensaje tiene información
        if(remoteMessage.getData().size() > 0){
            Log.d(this.getClass().getName(),"DATA: "+remoteMessage.getData() );
        }

        // Validamos que la notificación sea sinferente de null

        if(remoteMessage.getNotification() != null){

            sendNotification(remoteMessage.getNotification().getBody());

        }

    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }



    public void sendNotification(String message){


        Intent intent = new Intent(this, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent, PendingIntent.FLAG_ONE_SHOT);


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder notificationBuilder =  new NotificationCompat.Builder(this)
                                                               .setSmallIcon(R.drawable.ic_launcher_background)
                                                                .setContentTitle("PROMO HUNTERS")
                                                                .setContentText(message)
                                                                .setAutoCancel(true)
                                                                .setSound(defaultSoundUri)
                                                                .setContentIntent(pendingIntent);


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);



        notificationManager.notify(0, notificationBuilder.build());


    }





}
