package com.example.w23_g12_ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.w23_g12_ecommerceapp.dao.ProductDatabase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class Feature06Activity extends AppCompatActivity {


    String username;
    String address;
    String email;

    EditText usernameProfile;
    EditText addressProfile;
    EditText emailProfile;

    FirebaseAuth auth;

    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature06);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        usernameProfile = findViewById(R.id.edit_userName);
        addressProfile = findViewById(R.id.edit_userAdd);
        emailProfile = findViewById(R.id.edit_userEmail);
        auth = FirebaseAuth.getInstance();
        btnUpdate = findViewById(R.id.btnUpdateInfo);

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


        UserDBHelper userDBHelper = new UserDBHelper(this);
        List<UserModel> userModels = userDBHelper.getUser();
        String userEmail = auth.getCurrentUser().getEmail();
        int id = GetId(userEmail);

        try {
            if (id == -1) {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            }
            usernameProfile.setText(userModels.get(id - 1).userName);
            addressProfile.setText(userModels.get(id - 1).userAddress);
            emailProfile.setText(userModels.get(id - 1).userEmail);
            }catch (Exception e){
                Log.d("VC error", "Error in F06");
            }

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    username = usernameProfile.getText().toString();
                    address = addressProfile.getText().toString();
                    email = emailProfile.getText().toString();

                    if (isValidEmail(email)) {
                        try {
                            userDBHelper.updateUser(new UserModel(userModels.get(0).id, username, email, address));
                            auth.getCurrentUser().updateEmail(email);
                            Toast.makeText(Feature06Activity.this, "Update successful", Toast.LENGTH_SHORT).show();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            Toast.makeText(Feature06Activity.this, "Update data failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });


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

    public boolean isValidEmail(String emailInput) {

        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            return true;
        }else {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public int GetId(String userEmail) {
        UserDBHelper userDBHelper = new UserDBHelper(this);
        Cursor cursor = userDBHelper.getReadableDatabase().rawQuery("SELECT id FROM USERS WHERE userEmail=?", new String[]{userEmail});
        int num = -1;
        if (cursor.moveToFirst()) num = cursor.getInt(0);
        cursor.close();
        userDBHelper.close();
        return num;
    }


}