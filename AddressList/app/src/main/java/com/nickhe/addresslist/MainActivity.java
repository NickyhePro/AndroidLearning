package com.nickhe.addresslist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView addressListView;

    static ArrayList<String> addressList = new ArrayList<>();
    static ArrayList<LatLng> locations = new ArrayList<>();

    static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addressListView = findViewById(R.id.addressList);

        addressList.add("Add a new address...");
        locations.add(new LatLng(0,0));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, addressList);

        addressListView.setAdapter(adapter);

        addressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);

                intent.putExtra("placeNumber", position);

                startActivity(intent);

            }
        });
    }
}
