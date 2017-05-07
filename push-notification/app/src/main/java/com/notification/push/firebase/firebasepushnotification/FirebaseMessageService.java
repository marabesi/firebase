package com.notification.push.firebase.firebasepushnotification;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessageService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        Log.d(FirebaseTokenService.TAG, "From: " + remoteMessage.getFrom());

        Handler handler = new Handler(Looper.getMainLooper());

        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), remoteMessage.getNotification().getBody(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
