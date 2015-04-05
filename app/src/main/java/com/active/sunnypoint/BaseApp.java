package com.active.sunnypoint;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by cvnhan on 04-Apr-15.
 */
public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {

        }
    }
}
