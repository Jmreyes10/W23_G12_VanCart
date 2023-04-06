package com.example.w23_g12_ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPass_App extends AppCompatActivity {

    private TextInputEditText forEmail;
    private Button forgot;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass_app);

        forEmail = findViewById(R.id.editForgotEmail);
        forgot = findViewById(R.id.btnResend);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = forEmail.getText().toString();

                if (email.isEmpty()){
                    Toast.makeText(ForgotPass_App.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                }else {
                    forgetPassword();
                }
            }

            private void forgetPassword() {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgotPass_App.this, "Check your email for reset instructions", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Login_App.class));
                            finish();
                        }else {
                            Toast.makeText(ForgotPass_App.this, "Error " + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}