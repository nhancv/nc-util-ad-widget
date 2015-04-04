package com.active.sunnypoint;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import cvnhan.android.widget.CircleProgressBar;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar seekBarProgress, seekBarThickness;
        seekBarProgress = (SeekBar) findViewById(R.id.seekBar_progress);
        seekBarThickness = (SeekBar) findViewById(R.id.seekBar_thickness);
        final CircleProgressBar circleProgressBar = (CircleProgressBar) findViewById(R.id.custom_progressBar);


        seekBarProgress.setProgress((int) circleProgressBar.getProgress());

        seekBarProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b)
                    circleProgressBar.setProgressWithAnimation(i);
                else
                    circleProgressBar.setProgress(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarThickness.setProgress((int) circleProgressBar.getStrokeWidth());
        seekBarThickness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                circleProgressBar.setStrokeWidth(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
