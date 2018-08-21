package com.example.nickyhe.gridlayoutdemo;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    public void buttonPressed(View view){

        int id = view.getId();
        String ourId;

        ourId = view.getResources().getResourceEntryName(id);

        int resId = getResources().getIdentifier(ourId, "raw", "com.example.nickyhe.gridlayoutdemo");

        MediaPlayer mediaPlayer = MediaPlayer.create(this, resId);

        mediaPlayer.start();

        Log.i("Button pressed", ourId);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
