package com.example.farmeazy2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    String[] tiles = {"Change Password", "Log out"};
    ListView listView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        getSupportActionBar().hide();
        listView = findViewById(R.id.profile_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.profile_list, R.id.profile_tiles, tiles);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = adapter.getItem(i);
                if(str.equals("Log out")){
                    Intent intent = new Intent(Profile.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
