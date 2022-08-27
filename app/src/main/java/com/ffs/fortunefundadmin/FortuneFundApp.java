package com.ffs.fortunefundadmin;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.messaging.FirebaseMessaging;
import com.securepreferences.SecurePreferences;

import dagger.hilt.android.HiltAndroidApp;

/**
 * Created by hp on 26-09-2018.
 */


@HiltAndroidApp
public class FortuneFundApp extends Application {

    private static final String TAG = "App";
    private static final String contentType = "application/json";
    private static final String accept = "frontend-client";
    private SecurePreferences mSecurePrefs;
    private SecurePreferences mUserPrefs;
    // private static final String BASE_SERVICE_URL = Constants.BASE_URL;
    //private static final String BASE_SERVICE_URL = "https://zibito#env#.azurewebsites.net/api/";

    private static FortuneFundApp sID;
    public static Context context;


    private FirebaseCrashlytics crashlytics;


    public FortuneFundApp(){
        sID = this;
        context = this;
    }

    public static FortuneFundApp getInstance() {
        return sID;
    }

    public static Context getAppContext() {

        return sID.getApplicationContext();
    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //  MultiDex.install(base);
    }


    @Override
    public void onCreate() {
        super.onCreate();
      //  getAppContext();
       // getInstance();
        initApp();
    }

    private void initApp() {
        FirebaseMessaging.getInstance().subscribeToTopic("all");
    }


    public SharedPreferences getSharedPreferences() {
        if(mSecurePrefs==null){
            mSecurePrefs = new SecurePreferences(this, "", "my_fortunefund_store.xml");
            SecurePreferences.setLoggingEnabled(true);
        }
        return mSecurePrefs;
    }







}
