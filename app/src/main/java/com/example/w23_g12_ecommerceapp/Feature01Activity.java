package com.example.w23_g12_ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.w23_g12_ecommerceapp.dao.ProductDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Feature01Activity extends AppCompatActivity {

    private Spinner spinnerCategory;
    private RecyclerView revProducts;
    private TextView txtItemsCart;
    private Button btnViewCart;
    List<Product> productsInCart = new ArrayList<>();
    ProductAdapter productAdapter;
    ProductDatabase productDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature01);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtItemsCart = findViewById(R.id.txtItemsCart);
        spinnerCategory = findViewById(R.id.spinnerCategories);
        btnViewCart = findViewById(R.id.btnViewCart);

        productDatabase = Room.databaseBuilder(
                getApplicationContext(),
                ProductDatabase.class,
                "products_db"
        ).allowMainThreadQueries().build();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.prod_categories, android.R.layout.simple_spinner_item);

        spinnerCategory.setAdapter(adapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadData(spinnerCategory.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnViewCart.setOnClickListener((View v)-> {
            productDatabase.productDao().insert(productsInCart);
            int rowCount = productDatabase.productDao().getRowCount();
            if(rowCount==0)
                Toast.makeText(this,"You have to add at least one product!",Toast.LENGTH_SHORT).show();
            else
                startActivity(new Intent(Feature01Activity.this, ViewCart.class));
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

    @Override
    public void onRestart(){
        super.onRestart();
        productsInCart = productDatabase.productDao().getAll();
        txtItemsCart.setText("Number of items in cart: "+productsInCart.size());
        loadData(spinnerCategory.getSelectedItem().toString());
    }

    public void loadData(String prodCategory){
        API api = RetrofitClient.getInstance().getAPI();
        Call<List<Product>> call = api.getProductsByCategory(prodCategory);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Feature01Activity.this, response.code() + "", Toast.LENGTH_LONG).show();
                    return;
                }

                List<Product> products = response.body();
                revProducts = findViewById(R.id.revProducts);
                revProducts.setLayoutManager(new LinearLayoutManager(Feature01Activity.this));
                productAdapter = new ProductAdapter(Feature01Activity.this, products, txtItemsCart, productsInCart);
                revProducts.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(Feature01Activity.this, t.getMessage() + "", Toast.LENGTH_LONG).show();
            }
        });
    }

}