package com.example.farmeazy2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class opinion extends AppCompatActivity {
    String[] opinions = {"1. Title", "2. Title", "3. Title"};
    ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opinion);
        getSupportActionBar().hide();
        lv = (ListView) findViewById(R.id.op_card_list);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.opinion_card_list, R.id.home_feature, opinions);
        lv.setAdapter(adapter);
    }
}
