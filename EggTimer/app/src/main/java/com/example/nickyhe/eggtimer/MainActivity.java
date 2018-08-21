package com.example.nickyhe.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    SeekBar timerSeekBar;
    MediaPlayer mediaPlayer;
    Button button;
    int mins;
    int secs;
    String minsText;
    String secsText;

    public void setTimerText(int mins, int secs) {

        if(mins<10)
        {
            minsText = "0"+mins;
        }else{
            minsText = mins+"";
        }

        if(secs < 10)
        {
            secsText = "0"+secs;
        }else {
            secsText = secs+"";
        }

        timerTextView.setText(minsText + ":" + secsText);
    }

    public void timerStart(View view)
    {

        int duration = mins * 60 + secs;

        new CountDownTimer(duration*1000, 1000) {

            public void onTick(long millisec) {

                int mins = (int)(millisec /60000);
                int secs = (int)(millisec / 1000 - mins * 60);

                timerSeekBar.setEnabled(false);
                button.setEnabled(false);
                setTimerText(mins, secs);
            }

            public void onFinish() {

                setTimerText(0, 0);
                mediaPlayer.start();
                timerSeekBar.setEnabled(true);
                button.setEnabled(true);
            }
        }.start();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.timerTextView);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
        button = findViewById(R.id.controllerBtn);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                mins = progress / 60;
                secs = progress - mins * 60;

                setTimerText(mins, secs);

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
