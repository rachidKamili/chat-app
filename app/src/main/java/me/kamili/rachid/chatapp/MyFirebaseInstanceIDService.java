package me.kamili.rachid.chatapp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();
    private static final String FRIENDLY_ENGAGE_TOPIC = "friendly_engage";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "FCM Token: " + token);

        // Once a token is generated, we subscribe to topic.
        FirebaseMessaging.getInstance().subscribeToTopic(FRIENDLY_ENGAGE_TOPIC);
    }
}
