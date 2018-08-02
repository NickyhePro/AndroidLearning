package com.nicky.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void clickFunction(View view)
    {
        EditText usernameText = findViewById(R.id.inputTextField);
        EditText passwordText = findViewById(R.id.passwordTextField);

        Toast.makeText(MainActivity.this, "Hi "+usernameText.getText().toString()+"!", Toast.LENGTH_SHORT).show();

        Log.i("Info", "Username: "+usernameText.getText().toString()+", password: "+passwordText.getText().toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
