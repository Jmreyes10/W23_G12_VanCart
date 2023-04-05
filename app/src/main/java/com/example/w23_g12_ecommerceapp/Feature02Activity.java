package com.example.w23_g12_ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Feature02Activity extends AppCompatActivity {

    RatingBar ratingBarQ1, ratingBarQ2, ratingBarQ3, ratingBarQ4;
    ImageView ivRatingQ1, ivRatingQ2, ivRatingQ3, ivRatingQ4;
    Button btnSumbitRating;
    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;


//    FirebaseAuth auth;
//    Button button;
//    TextView textView;
//    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature02);

//        auth = FirebaseAuth.getInstance();
//        button = findViewById(R.id.logout);
//        textView = findViewById(R.id.user_details);
//        user = auth.getCurrentUser();
//
//        if (user == null) {
//            Intent intent = new Intent(getApplicationContext(), Login_App.class);
//            startActivity(intent);
//            finish();
//        }else {
//            textView.setText("Hi," + user.getEmail());
//        }
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(), Login_App.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();


        ratingBarQ1 = findViewById(R.id.ratingBarQ1);
        ratingBarQ2 = findViewById(R.id.ratingBarQ2);
        ratingBarQ3 = findViewById(R.id.ratingBarQ3);
        ratingBarQ4 = findViewById(R.id.ratingBarQ4);

        ivRatingQ1 = findViewById(R.id.ivRatingQ1);
        ivRatingQ2 = findViewById(R.id.ivRatingQ2);
        ivRatingQ3 = findViewById(R.id.ivRatingQ3);
        ivRatingQ4 = findViewById(R.id.ivRatingQ4);

        btnSumbitRating = findViewById(R.id.btnSumbitRating);

        ratingBarQ1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (ratingBarQ1.getRating()<=1)
                    ivRatingQ1.setImageResource(R.drawable.img_rating_very_bad);
                else if (ratingBarQ1.getRating()<=2)
                    ivRatingQ1.setImageResource(R.drawable.img_rating_poor);
                else if (ratingBarQ1.getRating()<=3)
                    ivRatingQ1.setImageResource(R.drawable.img_rating_medium);
                else if (ratingBarQ1.getRating()<=4)
                    ivRatingQ1.setImageResource(R.drawable.img_rating_good);
                else
                    ivRatingQ1.setImageResource(R.drawable.img_rating_excellent);
            }
        });

        ratingBarQ2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (ratingBarQ2.getRating()<=1)
                    ivRatingQ2.setImageResource(R.drawable.img_rating_very_bad);
                else if (ratingBarQ2.getRating()<=2)
                    ivRatingQ2.setImageResource(R.drawable.img_rating_poor);
                else if (ratingBarQ2.getRating()<=3)
                    ivRatingQ2.setImageResource(R.drawable.img_rating_medium);
                else if (ratingBarQ2.getRating()<=4)
                    ivRatingQ2.setImageResource(R.drawable.img_rating_good);
                else
                    ivRatingQ2.setImageResource(R.drawable.img_rating_excellent);
            }
        });

        ratingBarQ3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (ratingBarQ3.getRating()<=1)
                    ivRatingQ3.setImageResource(R.drawable.img_rating_very_bad);
                else if (ratingBarQ3.getRating()<=2)
                    ivRatingQ3.setImageResource(R.drawable.img_rating_poor);
                else if (ratingBarQ3.getRating()<=3)
                    ivRatingQ3.setImageResource(R.drawable.img_rating_medium);
                else if (ratingBarQ3.getRating()<=4)
                    ivRatingQ3.setImageResource(R.drawable.img_rating_good);
                else
                    ivRatingQ3.setImageResource(R.drawable.img_rating_excellent);
            }
        });

        ratingBarQ4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (ratingBarQ4.getRating()<=1)
                    ivRatingQ4.setImageResource(R.drawable.img_rating_very_bad);
                else if (ratingBarQ4.getRating()<=2)
                    ivRatingQ4.setImageResource(R.drawable.img_rating_poor);
                else if (ratingBarQ4.getRating()<=3)
                    ivRatingQ4.setImageResource(R.drawable.img_rating_medium);
                else if (ratingBarQ4.getRating()<=4)
                    ivRatingQ4.setImageResource(R.drawable.img_rating_good);
                else
                    ivRatingQ4.setImageResource(R.drawable.img_rating_excellent);
            }
        });

        btnSumbitRating.setOnClickListener((View v) -> {
            mEditor.putFloat("ratingQ1", ratingBarQ1.getRating());
            mEditor.putFloat("ratingQ2", ratingBarQ1.getRating());
            mEditor.putFloat("ratingQ3", ratingBarQ1.getRating());
            mEditor.putFloat("ratingQ4", ratingBarQ1.getRating());
            mEditor.commit();

            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            finish();
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
}