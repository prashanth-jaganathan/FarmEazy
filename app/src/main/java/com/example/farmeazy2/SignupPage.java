package com.example.farmeazy2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupPage extends AppCompatActivity {
    FirebaseAuth mAuth;
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
        mAuth = FirebaseAuth.getInstance();
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

        if(TextUtils.isEmpty(semail)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }
        else if(TextUtils.isEmpty(spass)){
            password.setError("Enter Password");
            password.requestFocus();
        }
        else if(TextUtils.isEmpty(scpass)){
            confirmPassword.setError("Re-type Password");
            confirmPassword.requestFocus();
        }
        else if(TextUtils.isEmpty(ccity)){
            city.setError("Enter City");
            city.requestFocus();
        }
        else if(TextUtils.isEmpty(cstate)){
            state.setError("Enter State");
            state.requestFocus();
        }
        else if(!semail.endsWith("@gmail.com") || semail.length()<=10){
            email.setError("Invalid Email ID");
            email.requestFocus();
        }
        else if(spass.length()<6){
            password.setError("Password should be atleast 6 characters long");
            password.requestFocus();
        }
        else if(!scpass.equals(spass)){
            confirmPassword.setError("Passwords don't match");
            password.hasFocus();
            confirmPassword.requestFocus();
        }
        else{
            createUser();
        }
    }
    public void createUser(){
        String user_email = email.getText().toString();
        String user_password = password.getText().toString();

        mAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupPage.this, HomePage.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void openLoginActivity(){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}
