package com.example.w23_g12_ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class Feature07 extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature07);

                ActionBar actionBar = getSupportActionBar();
                actionBar.setDisplayHomeAsUpEnabled(true);

                recyclerView = findViewById(R.id.recyclerOrders);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setHasFixedSize(true);

                OrderHistoryDBHelper orderHistoryDBHelper = new OrderHistoryDBHelper(this);
                List<OrderModel> orderModels = orderHistoryDBHelper.getOrderHistory();

                if (orderModels.size() > 0) {
                    OrderAdapterClass orderAdapterClass = new OrderAdapterClass(orderModels, Feature07.this);
                    recyclerView.setAdapter(orderAdapterClass);
                } else{
                    Toast.makeText(this, "There are no recently ordered items", Toast.LENGTH_SHORT).show();
                }

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