//package com.example.w23_g12_ecommerceapp;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class Feature01Activity extends AppCompatActivity {
//
//    private RecyclerView revProducts;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_feature01);
//
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//
//        API api = RetrofitClient.getInstance().getAPI();
//        Call<List<Product>> call = api.getProducts();
//
//        call.enqueue(new Callback<List<Product>>() {
//            @Override
//            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(Feature01Activity.this, response.code() + "", Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                List<Product> products = response.body();
//                revProducts = findViewById(R.id.revProducts);
//                revProducts.setLayoutManager(new LinearLayoutManager(Feature01Activity.this));
//                revProducts.setAdapter(new ProductAdapter(Feature01Activity.this, products));
//            }
//
//            @Override
//            public void onFailure(Call<List<Product>> call, Throwable t) {
//                Toast.makeText(Feature01Activity.this, t.getMessage() + "", Toast.LENGTH_LONG).show();
//            }
//
//        });
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                this.finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//}