package com.example.farmeazy2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignupPage extends AppCompatActivity {
    EditText email, password, confirmPassword, city, state;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup_page);
        getSupportActionBar().hide();
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        confirmPassword = findViewById(R.id.signup_confirm_password);
        city = findViewById(R.id.signup_city);
        state = findViewById(R.id.signup_state);
    }
    public void clickSignup(View v){
        openHomeActivity();
    }
    public void clickLogin(View v){
        openLoginActivity();
    }
    public void openHomeActivity(){

        String semail = email.getText().toString();
        String spass = password.getText().toString();
        String scpass = confirmPassword.getText().toString();
        String ccity = city.getText().toString();
        String cstate = state.getText().toString();

        if(semail.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_LONG).show();
        }
        else if(spass.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_LONG).show();
        }
        else if(scpass.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_LONG).show();
        }
        else if(ccity.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter City", Toast.LENGTH_LONG).show();
        }
        else if(cstate.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter State", Toast.LENGTH_LONG).show();
        }
        else if(!semail.endsWith("@gmail.com") || semail.length()<=10){
            Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_LONG).show();
        }
        else if(spass.length()<6){
            Toast.makeText(getApplicationContext(), "Password should be atleast 6 characters", Toast.LENGTH_SHORT).show();
        }
        else if(!scpass.equals(spass)){
            Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_LONG).show();
        }
        else if(ccity.equals("")){
            Toast.makeText(getApplicationContext(), "Enter City", Toast.LENGTH_SHORT).show();
        }
        else if(cstate.equals("")){
            Toast.makeText(getApplicationContext(), "Enter State", Toast.LENGTH_SHORT).show();
        }
        else{

            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
        }
    }
    public void openLoginActivity(){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}
