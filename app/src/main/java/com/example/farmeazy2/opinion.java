package com.example.farmeazy2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class opinion extends AppCompatActivity {
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> contents = new ArrayList<>();
    ArrayList<String> authors = new ArrayList<>();
    ListView lv;
    blog b;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opinion);
        getSupportActionBar().hide();
        lv = (ListView) findViewById(R.id.op_card_list);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),titles, contents, authors);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://farmeazy-9b87e-default-rtdb.firebaseio.com/");
        DatabaseReference databaseReference;
        databaseReference = database.getReference();
        //databaseReference.child("farmeazy-9b87e-default-rtdb");
        //databaseReference.child("News");
        databaseReference.child("farmeazy-9b87e-default-rtdb").child("News").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                titles.clear();
                contents.clear();
                authors.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    blog b = snapshot.getValue(blog.class);
                    titles.add(b.getTitle());
                    //System.out.println(b.getTitle());
                    contents.add(b.getContent());
                    authors.add(b.getAuthor());
                    customAdapter.notifyDataSetChanged();
                    //System.out.println(b.getContent());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lv.setAdapter(customAdapter);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists())
//                {
//                    for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){
//                        blog b = childSnapshot.child("News").getValue(blog.class);
//                        System.out.println(b.title);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }
}
