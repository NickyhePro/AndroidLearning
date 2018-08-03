package com.nicky.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void convert(View view){

        TextView textView = findViewById(R.id.currencyText);
        double amount = textView.getTextSize()*5;
        Toast.makeText(MainActivity.this, "$"+amount, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
