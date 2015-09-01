package com.active.sunnypoint;

import android.app.Activity;
import android.os.Bundle;

import timber.log.Timber;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(MainActivity.class.getSimpleName());
        setContentView(R.layout.activity_main);

//        ButterKnife.inject(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ButterKnife.reset(this);
    }
}
