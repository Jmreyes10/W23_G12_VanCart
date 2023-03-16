package com.example.w23_g12_ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginNewActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin ,btnSignUp;
    TextView txtViewForgotPasswrod;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);

        username =findViewById(R.id.username1);
        password =findViewById(R.id.password1);
        btnlogin =findViewById(R.id.btnsignin1);
        btnSignUp=findViewById(R.id.btnsignup);
        txtViewForgotPasswrod=findViewById(R.id.txtViewForgotPassword);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginNewActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){


                        Toast.makeText(LoginNewActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), MenuActivity.class);
                        Bundle bundle =new Bundle();
                        bundle.putString("NAME",user);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginNewActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });

        txtViewForgotPasswrod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ForgotActivity.class);
                startActivity(intent);
            }
        });


    }
}