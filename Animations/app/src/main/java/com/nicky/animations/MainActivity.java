package com.nicky.animations;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    public void fade(View view){

        ImageView batman1 = findViewById(R.id.batman1);
        ImageView batman2 = findViewById(R.id.batman2);

        //Fade in/out an image, alpha(0) means invisible, alpha(1) means fully visible
        batman1.animate().scaleX(0.5f).scaleY(0.5f).rotation(720f).alpha(0).setDuration(2000);
        batman2.animate().scaleY(1f).scaleX(1f).alpha(1).rotation(720f).setDuration(2000);

        //Rotate an image
        //batman1.animate().rotation(180f).setDuration(2000);

        //Scaling an image
        //batman1.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
