package com.active.sunnypoint.dagger;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by NhanCao on 03-Sep-15.
 */
@Singleton
@Component(modules = MainModule.class)
public interface SingletonComponent {
    Bus bus();
}
