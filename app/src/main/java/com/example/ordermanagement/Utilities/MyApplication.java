package com.example.ordermanagement.Utilities;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import static android.support.constraint.Constraints.TAG;

public class MyApplication extends Application
{
    private static Context context;
    private static String fcm;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this;

        fcm = FirebaseInstanceId.getInstance().getToken();

    }

    public static String getFcm() {

        fcm = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "Fcm is " + fcm);
        return fcm;
    }

    public static Context getContext() {
        return context;
    }
}
