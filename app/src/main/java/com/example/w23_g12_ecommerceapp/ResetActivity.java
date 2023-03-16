package com.example.w23_g12_ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
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
    String usernamehere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        txtViewReset=findViewById(R.id.txtViewReset);
        txtpassword=findViewById(R.id.txtpassword);
        txtComfirm_password=findViewById(R.id.txtComfirm_password);
        btnChange_Password=findViewById(R.id.btnChange_Password);
        Intent intent=getIntent();

        usernamehere=intent.getStringExtra("username");
        txtViewReset.setText("Enter the new password for "+usernamehere);
        DB=new DBHelper(this);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)    {
            NotificationChannel channel=new NotificationChannel("My Notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        btnChange_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = usernamehere;
                String password = txtpassword.getText().toString();
                String compassword = txtComfirm_password.getText().toString();

                if (password.equals(compassword)) {


                    Boolean checkpasswordupdate = DB.updatepassword(user, password);
                    if (checkpasswordupdate == true) {
                        NotificationCompat.Builder builder=new NotificationCompat.Builder(ResetActivity.this,"My Notification");
                        builder.setContentTitle("New Notification");
                        builder.setContentText("Password changed successfully for " +user);
                        builder.setSmallIcon(R.mipmap.ic_launcher_logo);
                        builder.setAutoCancel(true);
                        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(ResetActivity.this);
                        managerCompat.notify(1,builder.build());
                        Intent intent = new Intent(getApplicationContext(), LoginNewActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetActivity.this, "Password not updated successfully", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(ResetActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}