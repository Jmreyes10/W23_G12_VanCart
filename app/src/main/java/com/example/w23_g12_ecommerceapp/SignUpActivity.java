package com.example.w23_g12_ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
//
//    EditText username, password, repassword;
//    Button signup, signin;
//    DBHelper DB;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//
//        username = findViewById(R.id.username);
//        password = findViewById(R.id.editPasswordText);
//        repassword = findViewById(R.id.repassword);
//        signup =  findViewById(R.id.btnsignup);
//        signin = findViewById(R.id.btnsignin);
//        DB = new DBHelper(this);
//
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String user = username.getText().toString();
//                String pass = password.getText().toString();
//                String repass = repassword.getText().toString();
//
//                if(user.equals("")||pass.equals("")||repass.equals(""))
//                    Toast.makeText(SignUpActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
//                else{
//                    if(pass.equals(repass)){
//                        Boolean checkuser = DB.checkusername(user);
//                        if(checkuser==false){
//                            Boolean insert = DB.insertData(user, pass);
//                            if(insert==true){
//                                Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
//                                startActivity(intent);
//                            }else{
//                                Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        else{
//                            Toast.makeText(SignUpActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
//                        }
//                    }else{
//                        Toast.makeText(SignUpActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
//                    }
//                } }
//        });
//
//        signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), LoginNewActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
}