package com.example.w23_g12_ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Feature06Activity extends AppCompatActivity {

    DBHelper dbHelper;

    String username;
    String address;
    String phone;

    TextView usernameProfile;
    TextView addressProfile;
    TextView phoneProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature06);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        usernameProfile = findViewById(R.id.username_profile);
        addressProfile = findViewById(R.id.user_address);
        phoneProfile = findViewById(R.id.user_phone);

        dbHelper = new DBHelper(this);
//        Cursor cursor = dbHelper.getText();
//        if (cursor.getCount()==0){
//            Toast.makeText(this, "User details don't exist", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        StringBuffer buffer = new StringBuffer();
//
//        while (cursor.moveToNext()){
//            username = cursor.getString(0);
//            phone = cursor.getString(1);
//            address = cursor.getString(2);
//        }

        usernameProfile.setText("Hi, "+ username);
        addressProfile.setText(address);
        phoneProfile.setText(phone);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}