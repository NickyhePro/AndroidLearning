package com.nickhe.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Name of the package and mode

        SharedPreferences sharedPreferences =
                this.getSharedPreferences("com.nickhe.sharedpreference", Context.MODE_PRIVATE);

        //sharedPreferences.edit().putString("username", "Nick").apply();

//        String username = sharedPreferences.getString("username", "Alex");
//
//        System.out.println(username);

//        ArrayList<String> friends = new ArrayList<>();
//
//        friends.add("James");
//        friends.add("Alex");
//
//        try {
//
//            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//        }

        ArrayList<String> newFriends = new ArrayList<>();

        try {
            newFriends = (ArrayList<String>)ObjectSerializer.deserialize(sharedPreferences
                    .getString("friends", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("newFriends2", newFriends.toString());
    }
}
