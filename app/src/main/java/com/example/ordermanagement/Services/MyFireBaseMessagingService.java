package com.example.ordermanagement.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.ordermanagement.R;
import com.example.ordermanagement.SplashActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class MyFireBaseMessagingService extends FirebaseMessagingService
{

    @Override
    public void onNewToken(String s)
    {
        Log.e("FCm","s");
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        Bitmap bitmap = null;
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Log.i("Message recieved",remoteMessage.getData()+"");


        Intent intent=new Intent(this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notification=new NotificationCompat.Builder(this);
        notification.setContentTitle(remoteMessage.getData().get("title"));
        notification.setContentText(remoteMessage.getData().get("message"));
        notification.setAutoCancel(true);
        notification.setSound(defaultSoundUri);
        
        URL url = null;
        try {
            url = new URL(remoteMessage.getData().get("image"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            bitmap= BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        notification.setSmallIcon(R.drawable.logo);
        notification.setLargeIcon(bitmap);
        notification.setContentIntent(pendingIntent);

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notification.build());

    }
}
