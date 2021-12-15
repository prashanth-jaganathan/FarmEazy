package com.example.farmeazy2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {
    String[] features = {"Predict Crop", "Blogs", "Profile"};
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        getSupportActionBar().hide();
        listView = (ListView) findViewById(R.id.card_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.home_card_list, R.id.home_feature, features);
        listView.setAdapter(adapter);

    }
}
