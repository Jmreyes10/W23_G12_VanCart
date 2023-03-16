package com.example.w23_g12_ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetActivity extends AppCompatActivity {

    TextView txtViewReset;
    EditText txtpassword;
    EditText txtComfirm_password;
    Button btnChange_Password;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        txtViewReset=findViewById(R.id.txtViewReset);
        txtpassword=findViewById(R.id.txtpassword);
        txtComfirm_password=findViewById(R.id.txtComfirm_password);
        btnChange_Password=findViewById(R.id.btnChange_Password);
        Intent intent=getIntent();
        txtViewReset.setText(intent.getStringExtra("username"));
        DB=new DBHelper(this);

        btnChange_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = txtViewReset.getText().toString();
                String password = txtpassword.getText().toString();
                String compassword = txtComfirm_password.getText().toString();

                if (password.equals(compassword)) {


                    Boolean checkpasswordupdate = DB.updatepassword(user, password);
                    if (checkpasswordupdate == true) {
                        Intent intent = new Intent(getApplicationContext(), LoginNewActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetActivity.this, "Password not updated successfully", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(ResetActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}