package com.candroid.app;

import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

public class CandroidApplication extends Application {

    private static final String TAG = CandroidApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.USE_CRASHLYTICS) {
            try {
                Crashlytics.start(this);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }
}