package com.nickhe.menudemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.english:
                chooseLanguage("English");
                return true;
            case R.id.chinese:
                chooseLanguage("Chinese");
                return true;
            default:
                return false;
        }
    }

    public void chooseLanguage(String language)
    {

        sharedPreferences.edit().putString("language", language).apply();

        textView.setText(language);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences("com.nickhe.menudemo", Context.MODE_PRIVATE);

        textView = findViewById(R.id.textView);

        String language = sharedPreferences.getString("language", "");

        if(language == "")
        {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Language select")
                    .setMessage("Which language do you prefer?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chooseLanguage("English");
                        }
                    })
                    .setNegativeButton("Chinese", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            chooseLanguage("Chinese");
                        }
                    })
                    .show();
        }else {
            textView.setText(language);
        }



    }
}
