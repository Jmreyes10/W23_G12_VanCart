//package com.example.w23_g12_ecommerceapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//public class ForgotActivity extends AppCompatActivity {
//
//    EditText txtUsername_Reset;
//    Button btnReset;
//    DBHelper DB;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forgot);
//
//        txtUsername_Reset=findViewById(R.id.txtUsername_Reset);
//        btnReset=findViewById(R.id.btnReset);
//
//        DB=new DBHelper(this);
//        btnReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String user=txtUsername_Reset.getText().toString();
//                Boolean checkuser=DB.checkusername(user);
//                if(checkuser==true){
//                    Intent intent=new Intent(getApplicationContext(),ResetActivity.class);
//                    intent.putExtra("username",user);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(ForgotActivity.this, "User does not exists!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//}