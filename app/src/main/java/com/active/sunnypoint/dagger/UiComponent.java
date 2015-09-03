package com.active.sunnypoint.dagger;

import com.active.sunnypoint.MainActivity;

import dagger.Component;

/**
 * Created by NhanCao on 03-Sep-15.
 */
@PerActivity
@Component(dependencies = SingletonComponent.class)
public interface UiComponent {
    void inject(MainActivity mainActivity);
}
