package com.example.farmeazy2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {
    String[] features = {"Predict Crop", "Expert Opinions", "Profile"};
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        getSupportActionBar().hide();
        listView = (ListView) findViewById(R.id.card_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.home_card_list, R.id.home_feature, features);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapter.getItem(i);
                //System.out.println(value);
                if(value.equals("Predict Crop")) {
                    Intent intent = new Intent(HomePage.this, predictions.class);
                    startActivity(intent);
                }
                if(value.equals("Expert Opinions")){
                    Intent intent = new Intent(HomePage.this, opinion.class);
                    startActivity(intent);
                }

                if(value.equals("Profile")){
                    Intent intent = new Intent(HomePage.this, Profile.class);
                    startActivity(intent);
                }

            }
        });
    }

}
