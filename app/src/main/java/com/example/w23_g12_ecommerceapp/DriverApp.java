package com.example.w23_g12_ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DriverApp extends AppCompatActivity {


    Button deliveredButton;
    DBHelper DB;
    private ListView orderListView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList=new ArrayList<Order>();
    List<Integer> OrderId =
            new ArrayList<>(Arrays.asList(1,2,3));
    List<String> OrderNames =
            new ArrayList<>(Arrays.asList("John","Peter","Andrew"));
    List<String> OrderAddress =
            new ArrayList<>(Arrays.asList("123 Main St, Anytown, USA 12345","456 Park Ave, Somewhere, USA 67890","456 Park Ave, Somewhere, USA 67890"));
    List<String> OrderStatus =
            new ArrayList<>(Arrays.asList("Deliver","Deliver","Delivered\u2713"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_app);
        DB = new DBHelper(this);

        orderListView = (ListView) findViewById(R.id.order_list);

        // Initialize the order list


        //LoadModelData();
        Log.d("AUDDEMO",orderList.size() + " Orders ");

        // Populate the order list from the SQLite database
        SQLiteDatabase db = this.DB.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM orders", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("order_id"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("customer_name"));
                @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("customer_address"));
                //int status = cursor.getInt(cursor.getColumnIndex("delivery_status"));
                Order order = new Order(id, name, address);
                orderList.add(order);
            } while (cursor.moveToNext());
        }

        // Initialize the order adapter and set it to the list view
        orderAdapter = new OrderAdapter(this, R.layout.order_item, orderList);
        orderListView.setAdapter(orderAdapter);

//        deliveredButton = findViewById(R.id.order_delivered_button);
//        deliveredButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                deliveredButton.setText("Delivered\u2713");
//            }
//        });

        // Set a click listener on the list view items
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected order
                Order order = orderList.get(position);

                // Launch the Google Maps app and show the customer's address
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + order.getCustomerAddress());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

    private void LoadModelData() {
        for (int i = 0; i < OrderId.size(); i++){
            Order order = new Order(OrderId.get(i), OrderNames.get(i), OrderAddress.get(i), OrderStatus.get(i));
            //Song eachSong = new Song(SongNames.get(i),SongPics.get(i),SongRaws.get(i));
            orderList.add(order);
            //SongList.add(eachSong);
        }
    }

//    private SQLiteDatabase getReadableDatabase() {
//        // TODO: Implement SQLite database
//        return null;
//    }
}