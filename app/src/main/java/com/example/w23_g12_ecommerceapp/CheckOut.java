package com.example.w23_g12_ecommerceapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.auth.FirebaseAuth;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class CheckOut extends AppCompatActivity {
    private PlacesClient placesClient;
    private AutocompleteSupportFragment autocompleteFragment;

    private EditText editText;
    TextView textView,txtDistInfo;

    Button btnPayment;
    PaymentSheet paymentSheet;
    String paymentIntentClientSecret;
    PaymentSheet.CustomerConfiguration customerConfig;

    Double totalOrder;
    DBHelper dbHelper;
    FirebaseAuth mAuth;

    String valueToPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        //textView.setText("Search for your delivery location here");
        txtDistInfo=findViewById(R.id.txtDistInfo);


        Places.initialize(getApplicationContext(),"AIzaSyDcxkAk1tO98SwdkKAnSNYMTUVCvoUfijI");

        placesClient = Places.createClient(this);



        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        //autocompleteFragment.setTypeFilter(TypeFilter.ADDRESS);
        //autocompleteFragment.setTypeFilter(TypeFilter.ESTABLISHMENT);
        //autocompleteFragment.setTypeFilter(TypeFilter.REGIONS);
        autocompleteFragment.setLocationBias(RectangularBounds.newInstance(
                new LatLng(48.2559, -139.0605), // Southwest corner of BC
                new LatLng(60.0000, -114.0000))); // Northeast corner of BC
        autocompleteFragment.setCountries("CA");

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID,Place.Field.NAME,Place.Field.LAT_LNG));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {

                // TODO: Get info about the selected place.
                Log.e(TAG, "Place: " + place.getName() + ", " + place.getId());


                //LatLng selectedLocation = place.getLatLng();
                LatLng selectedLocation = place.getLatLng();
                double latitude = 0;
                double longitude = 0;
                if (selectedLocation != null) {
                    latitude = selectedLocation.latitude;
                    longitude = selectedLocation.longitude;

                    Log.e(TAG, "Latitude: " + latitude + ", Longitude: " + longitude);

                }

                //double lon=selectedLocation.latitude;

                //Log.e(TAG, "LongLan: " + lon + ", ");
                //getCurrentLocation();

                // Calculate the distance between the two LatLng objects
                float[] results = new float[1];
                Location.distanceBetween(latitude, longitude,
                        49.2556684, -123.0431944, results);
                float distanceInMeters = results[0];

                // Convert the distance to kilometers
                float distanceInKm = distanceInMeters / 1000;
                distanceInKm+=0.6;

                Float Time=distanceInKm*2;
                Time+=60;

                // Display the distance in a Toast message
                Toast.makeText(CheckOut.this, "Distance and Time Calculated", Toast.LENGTH_SHORT).show();

                textView=findViewById(R.id.txtView);
                txtDistInfo.setText("Distance: " + String.format("%.2f", distanceInKm) + " km" + "\nEstimated time of Arrival:"+ String.format("%.2f", Time) + "mins");
            }


            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Log.e(TAG, "An error occurred: " + status);
            }
        });

        btnPayment = findViewById(R.id.btnPayment);
        paymentSheet = new PaymentSheet(this, this::onPaymentSheetResult);
        mAuth = FirebaseAuth.getInstance();

        //getting the data of price from previous activity
        Bundle bundle = getIntent().getExtras();
        totalOrder = bundle.getDouble("TOTAL");
        valueToPass = String.valueOf((int)Math.round(totalOrder));

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CheckOut.this, totalOrder.toString(), Toast.LENGTH_SHORT).show();
                getDetails();
//                String addressTEXT = editText.getText().toString();
//                Boolean save = dbHelper.saveUSerData("", "9090909090",addressTEXT);
//                if (TextUtils.isEmpty(addressTEXT)){
//                    Toast.makeText(CheckOut.this, "Address cannot be empty", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                else {
//                    if (save==true){
//                        Toast.makeText(CheckOut.this, save.toString(), Toast.LENGTH_SHORT).show();
//                        getDetails();
//                    }else {
//                        dbHelper.updateAddress(addressTEXT,mAuth.getCurrentUser().getEmail(),"604-123-9001", addressTEXT);
//                        getDetails();
//                        return;
//                    }
//                }
            }
        });

        editText = findViewById(R.id.editTextTextPostalAddress);
        dbHelper = new DBHelper(this);


    }

    void getDetails(){
        Fuel.INSTANCE.post("https://us-central1-csis3175-7b517.cloudfunctions.net/stripePayment?amt=" + valueToPass  ,null).responseString(new Handler<String>() {
            @Override
            public void success(String s) {
                try {
                    JSONObject result = new JSONObject(s);
                    customerConfig = new PaymentSheet.CustomerConfiguration(
                            result.getString("customer"),
                            result.getString("ephemeralKey")
                    );
                    paymentIntentClientSecret = result.getString("paymentIntent");
                    PaymentConfiguration.init(getApplicationContext(), result.getString("publishableKey"));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showStripePmtSheet();
                        }
                    });

                }catch (JSONException e){
                    Toast.makeText(CheckOut.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(@NonNull FuelError fuelError) {

            }
        });
    }

    void showStripePmtSheet(){

        final PaymentSheet.Configuration configuration = new PaymentSheet.Configuration.Builder("VanCart.")
                .customer(customerConfig)
                .allowsDelayedPaymentMethods(true)
                .build();
        paymentSheet.presentWithPaymentIntent(
                paymentIntentClientSecret,
                configuration
        );

    }
    void onPaymentSheetResult(final PaymentSheetResult paymentSheetResult){
        if (paymentSheetResult instanceof PaymentSheetResult.Canceled) {
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
        } else if (paymentSheetResult instanceof PaymentSheetResult.Failed) {
            Toast.makeText(this, ((PaymentSheetResult.Failed) paymentSheetResult).getError().toString(), Toast.LENGTH_SHORT).show();
        } else if (paymentSheetResult instanceof PaymentSheetResult.Completed) {
            // Display for example, an order confirmation screen
            startActivity(new Intent(this,ThankYou.class));
            Toast.makeText(this, "Completed", Toast.LENGTH_SHORT).show();        }
    }
    }


