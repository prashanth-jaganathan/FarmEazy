package com.example.farmeazy2;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {
    EditText email, password;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_page);
        getSupportActionBar().hide();
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
    }
    public void clickSignup(View v){
        openSignupActivity();
    }
    public void clickLogin(View v){
        openHomeActivity();
    }
    public void openHomeActivity(){
        String str = email.getText().toString();
        String pass=password.getText().toString();
        if((str.isEmpty() || str.equals(" ")) && (pass.isEmpty() || pass.equals(" ")))
            Toast.makeText(getApplicationContext(), "Enter Email and Password", Toast.LENGTH_LONG).show();
        else if(str.isEmpty() || str.equals(" "))
            Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_LONG).show();
        else if(pass.isEmpty() || pass.equals(" "))
            Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_LONG).show();
        else if(!str.endsWith("@gmail.com")) {
            Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_LONG).show();
            email.setText("");
        }
        else {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
        }
    }
    public void openSignupActivity(){
        Intent intent = new Intent(this, SignupPage.class);
        startActivity(intent);
    }
}
