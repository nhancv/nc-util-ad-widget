package com.active.sunnypoint;

import android.app.Application;

import com.active.sunnypoint.dagger.DaggerSingletonComponent;
import com.active.sunnypoint.dagger.MainModule;
import com.active.sunnypoint.dagger.SingletonComponent;

import timber.log.Timber;

/**
 * Created by cvnhan on 04-Apr-15.
 */
public class BaseApp extends Application {
    private SingletonComponent singletonComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        singletonComponent = DaggerSingletonComponent.builder().mainModule(new MainModule()).build();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {

        }
    }

    public SingletonComponent getSingletonComponent() {
        return singletonComponent;
    }
}
