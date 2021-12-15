package com.example.farmeazy2;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_page);
        getSupportActionBar().hide();
    }
    public void clickSignup(View v){
        openSignupActivity();
    }
    public void clickLogin(View v){
        openHomeActivity();
    }
    public void openHomeActivity(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
    public void openSignupActivity(){
        Intent intent = new Intent(this, SignupPage.class);
        startActivity(intent);
    }
}
