package com.active.sunnypoint.dagger;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NhanCao on 03-Sep-15.
 */
@Module
public class MainModule {
    @Provides
    @Singleton
    Bus proviceBus() {
        return new Bus();
    }

}
