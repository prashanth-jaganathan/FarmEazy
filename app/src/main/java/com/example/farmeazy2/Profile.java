package com.example.farmeazy2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {

    String[] tiles = {"Change Password", "Log out"};
    ListView listView;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        listView = findViewById(R.id.profile_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.profile_list, R.id.profile_tiles, tiles);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = adapter.getItem(i);
                if(str.equals("Log out")){
                    mAuth.signOut();
                    Intent intent = new Intent(Profile.this, MainActivity.class);
                    Toast.makeText(getApplicationContext(), "Logged out Successfully!!", Toast.LENGTH_LONG);
                    startActivity(intent);
                }
                else if(str.equals("Change Password"))
                {
                    changePassword(view);
                }
            }
        });

    }
    public void changePassword(View view)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();


        final EditText resetPass = new EditText(view.getContext());
        resetPass.setHint("New Password");
        resetPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

        final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
        passwordResetDialog.setTitle("Change Password");
       // passwordResetDialog.setMessage("\nEnter New Password");
        passwordResetDialog.setView(resetPass, 60,150,15,80);
        passwordResetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        passwordResetDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               // System.out.println("In Confirm");
                user.updatePassword(resetPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       // System.out.println("Completed");
                        if(task.isSuccessful())
                            Toast.makeText(getApplicationContext(), "Password changed successfully!!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), task.getException().getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        //passwordResetDialog.setView(10);
        passwordResetDialog.show();



    }

}
