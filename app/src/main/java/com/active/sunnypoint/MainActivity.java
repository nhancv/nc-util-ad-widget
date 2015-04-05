package com.active.sunnypoint;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cvnhan.android.widget.CircleProgressBar;
import timber.log.Timber;


public class MainActivity extends Activity {
    CountDownTimer mCountDownTimer;

    @InjectView(R.id.custom_progressBar)
    CircleProgressBar circleProgressBar;
    @InjectView(R.id.seekBar_progress)
    SeekBar seekBarProgress;
    @InjectView(R.id.seekBar_thickness)
    SeekBar seekBarThickness;
    @InjectView(R.id.clocktxt)
    TextView textViewShowTime;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(MainActivity.class.getSimpleName());
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
//        textViewShowTime.setTextAppearance(getApplicationContext(), R.style.blinkText);
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        textViewShowTime.setText(String.format("%03d",(int)circleProgressBar.getProgress()));
        seekBarProgress.setProgress((int) circleProgressBar.getProgress());
        seekBarProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                if (b)
//                    circleProgressBar.setProgressWithAnimation(i);
//                else
//                    circleProgressBar.setProgress(i);

                Timber.e(i + "");

                if(mCountDownTimer!=null){
                    mCountDownTimer.cancel();
                    textViewShowTime.clearAnimation();
                }
                mCountDownTimer = new CountDownTimer(seekBarProgress.getProgress() * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Timber.e("Tick of Progress " + millisUntilFinished);
                        circleProgressBar.setProgressWithAnimation(millisUntilFinished / 1000);
                        textViewShowTime.setText(String.format("%03d", millisUntilFinished / 1000));
                    }

                    @Override
                    public void onFinish() {
                        Timber.e("Finish()");
                        textViewShowTime.clearAnimation();
                        textViewShowTime.setText(String.format("%03d", 0 / 1000));
                        circleProgressBar.setProgressWithAnimation(0);
                    }
                };
                mCountDownTimer.start();
                textViewShowTime.startAnimation(anim);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
