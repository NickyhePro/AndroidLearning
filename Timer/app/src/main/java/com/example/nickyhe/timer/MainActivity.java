package com.example.nickyhe.timer;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    public void createHandler() {
        final Handler handler = new Handler();

        Runnable run = new Runnable() {
            @Override
            public void run() {

                Log.i("Runnable has run!", "a second has passed...");
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(run);
    }

    public void createTimer() {

        new CountDownTimer(10000, 1000) {

            public void onTick(long millisec) {

                Log.i("Seconds left to finish", millisec / 1000 + "");
            }

            public void onFinish() {

                Log.i("Done!", "Countdown timer finishes!");
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Two different ways to manager a timer

        //A countdown timer
        createTimer();

//        A timer you set repeatable tasks
//        createHandler();
    }
}
