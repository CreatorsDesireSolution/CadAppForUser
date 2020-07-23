package com.example.cadappforuser;

import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.cadappforuser.Appointment.FreelancerAppointment;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Act_MyMessagingService extends FirebaseMessagingService {

    Map<String, String> data;
    String body, title;
    Act_Session act_session;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        data = remoteMessage.getData();
        JSONObject jsonObject = new JSONObject(data);
        act_session = new Act_Session(getApplicationContext());



        try {
            Log.e("data", jsonObject.toString());
            body = jsonObject.getString("body");
            title = jsonObject.getString("title");
            Log.e("body", body);
            Log.e("title", title);
        } catch (JSONException e) {
            Log.e("exception>>>", e.toString() + "");
            e.printStackTrace();
            body = remoteMessage.getNotification().getBody();
            Log.e("body>>>", body + "");
        }


        if (act_session.flag.equals("0")){
            showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }
        if (act_session.flag.equals("1")){
            showNotification1(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }

        //  ApigettOTALlIKES();
    }





    public void showNotification(String title,String message) {



            Intent intent = new Intent(this, OrderSummary.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("body",message);
//        intent.putExtra("title",title);


            int requestCode = 0;
            PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotifications")
                    .setSmallIcon(R.drawable.splashlogo)
                    .setContentTitle(title)
                    .setContentText(message)
                    //  .setNumber(messageCount)
                    .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    // Set the intent that will fire when the user taps the notification
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);


            NotificationManagerCompat manager = NotificationManagerCompat.from(this);
            manager.notify(999, builder.build());



    }


    public void showNotification1(String title,String message) {


        Intent intent = new Intent(this, FreelancerAppointment.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("body",message);
//        intent.putExtra("title",title);


        int requestCode = 0;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotifications")
                .setSmallIcon(R.drawable.splashlogo)
                .setContentTitle(title)
                .setContentText(message)
                //  .setNumber(messageCount)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);


        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(999, builder.build());


//
    }
    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }
}


