package com.example.nickyhe.listviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.myListView);

        ArrayList<String> list = new ArrayList<>();
        list.add("Nick");
        list.add("Alex");
        list.add("James");
        list.add("Mike");

        ArrayAdapter<String> adapter =           //context, itemType, source
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String greeting = "Hello "+parent.getItemAtPosition(position).toString();

                Toast.makeText(getApplicationContext(), greeting, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
