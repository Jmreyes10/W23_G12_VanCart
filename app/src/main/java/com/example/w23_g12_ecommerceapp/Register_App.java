package com.example.w23_g12_ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register_App extends AppCompatActivity {

    TextInputEditText editEmailText, editPasswordText,editFullName;
    Button btnRegister;

    FirebaseAuth mAuth;

    ProgressBar progressBar;

    TextView loginNow;
    DBHelper DB;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_app);
        editFullName=findViewById(R.id.editFullName);
        editEmailText = findViewById(R.id.editForgotEmail);
        editPasswordText = findViewById(R.id.editPasswordText);
        btnRegister = findViewById(R.id.btnRegister);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        loginNow = findViewById(R.id.loginNow);
        DB = new DBHelper(this);
        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login_App.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password,fullname;

                email = editEmailText.getText().toString();
                password = editPasswordText.getText().toString();
                fullname = editFullName.getText().toString();


                if (TextUtils.isEmpty(fullname)) {
                    Toast.makeText(Register_App.this, "Full name cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register_App.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register_App.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Boolean checkuser = DB.checkusername(email);
                                    if(checkuser==false){
                                        Boolean insert = DB.insertData(email, fullname, password);
                                        if(insert==true){
                                            Toast.makeText(Register_App.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(Register_App.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else{
                                        Toast.makeText(Register_App.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                                    }

                                    Toast.makeText(Register_App.this, "Account created successfully..",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login_App.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Register_App.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    String s = "Sign up Failed" + task.getException();
                                    Toast.makeText(Register_App.this, s,
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}