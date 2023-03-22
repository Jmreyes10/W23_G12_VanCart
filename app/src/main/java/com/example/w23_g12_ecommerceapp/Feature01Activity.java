package com.example.w23_g12_ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature01);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtItemsCart = findViewById(R.id.txtItemsCart);
        spinnerCategory = findViewById(R.id.spinnerCategories);
        btnViewCart = findViewById(R.id.btnViewCart);

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
            int numItems = productsInCart.size();
            long[] prodsId = new long[numItems];
            String[] prodsCode = new String[numItems];
            String[] prodsName = new String[numItems];
            String[] prodCategory = new String[numItems];
            String[] prodImgUrl = new String[numItems];
            double[] prodsPrice = new double[numItems];
            for(int c=0;c<numItems;c++){
                prodsId[c]=productsInCart.get(c).getId();
                prodsCode[c]=productsInCart.get(c).getProdCode();
                prodsName[c]=productsInCart.get(c).getProdName();
                prodCategory[c]=productsInCart.get(c).getProdCategory();
                prodImgUrl[c]=productsInCart.get(c).getProdImgUrl();
                prodsPrice[c]=productsInCart.get(c).getProdPrice();
            }
            
            Intent intent = new Intent(this, ViewCart.class);
            Bundle bundle = new Bundle();
            bundle.putInt("numItems", numItems);
            bundle.putLongArray("prodsId", prodsId);
            bundle.putStringArray("prodsCode",prodsCode);
            bundle.putStringArray("prodsName",prodsName);
            bundle.putStringArray("prodCategory",prodCategory);
            bundle.putStringArray("prodImgUrl",prodImgUrl);
            bundle.putDoubleArray("prodsPrice",prodsPrice);
            intent.putExtras(bundle);
            startActivity(intent);
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
                revProducts.setAdapter(new ProductAdapter(Feature01Activity.this, products, txtItemsCart, productsInCart));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(Feature01Activity.this, t.getMessage() + "", Toast.LENGTH_LONG).show();
            }
        });
    }

}