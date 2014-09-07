package com.candroid.app;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

public class CandroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.USE_CRASHLYTICS) {
            Crashlytics.start(this);
        }
    }
}