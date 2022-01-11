package com.example.farmeazy2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    EditText email, password;
    FirebaseAuth mAuth;
    TextView forgotPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_page);
        getSupportActionBar().hide();
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        mAuth = FirebaseAuth.getInstance();
        forgotPassword = findViewById(R.id.forgot_password);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetMail = new EditText(view.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("\nEnter your Email ID");
                passwordResetDialog.setView(resetMail, 55,0,15,0);
                passwordResetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                passwordResetDialog.setPositiveButton("Send Link", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String email = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Reset link has been sent to your Email.", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Error! Reset Link not sent. "+e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                //passwordResetDialog.setView(10);
                passwordResetDialog.show();
            }
        });
    }
    public void clickSignup(View v){
        openSignupActivity();
    }
    public void clickLogin(View v){
        openHomeActivity();
    }
    public void openHomeActivity(){
        String str = email.getText().toString();
        String pass= password.getText().toString();
       if(TextUtils.isEmpty(str) && TextUtils.isEmpty(pass)){
           email.setError("Email cannot be empty");
           password.setError("Enter password");
           email.requestFocus();
           password.requestFocus();
       }
        else if(TextUtils.isEmpty(str)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }
//        else if(str.isEmpty() || str.equals(" "))
//            Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_LONG).show();
        else if(TextUtils.isEmpty(pass)){
            password.setError("Enter password");
            password.requestFocus();
        }
        else if(!str.endsWith("@gmail.com")) {
            email.setError("Invalid Email ID");
            email.setText("");
            email.requestFocus();
        }
        else {
           loginUser();
        }
    }
    public void loginUser(){
        String user_email = email.getText().toString();
        String user_password = password.getText().toString();
        
        mAuth.signInWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginPage.this, HomePage.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Login Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        
    }
    public void openSignupActivity(){
        Intent intent = new Intent(this, SignupPage.class);
        startActivity(intent);
    }
}











