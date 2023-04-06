package com.example.w23_g12_ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.w23_g12_ecommerceapp.dao.ProductDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ViewCart extends AppCompatActivity {

    private RecyclerView rvProductsInCart;
    private TextView tvSubTotalVal, tvShippingVal, tvTaxesVal, tvTotalVal;
    private Button btnCancelViewCart;
    private Button btnCheckOut;
    private List<Product> productsInCart;
    private List<Integer> prodQtty = new ArrayList<>();
    private final double TAX_RATE = 0.10;
    private double shipping = 20.00;

    private double total;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        ProductDatabase productDatabase = Room.databaseBuilder(
                getApplicationContext(),
                ProductDatabase.class,
                "products_db"
        ).allowMainThreadQueries().build();

        rvProductsInCart = findViewById(R.id.rvProductsInCart);
        tvSubTotalVal = findViewById(R.id.tvSubTotalVal);
        tvShippingVal = findViewById(R.id.tvShippingVal);
        tvTaxesVal = findViewById(R.id.tvTaxesVal);
        tvTotalVal = findViewById(R.id.tvTotalVal);
        btnCancelViewCart = findViewById(R.id.btnCancelViewCart);
        btnCheckOut=findViewById(R.id.btnCheckOut);

        productsInCart = productDatabase.productDao().getAll();
        for (int i = 0; i < productsInCart.size(); i++)
            prodQtty.add(1);

        rvProductsInCart.setLayoutManager(new LinearLayoutManager(ViewCart.this));
//        ProductCartAdapter productCartAdapter = new ProductCartAdapter(ViewCart.this, productDatabase.productDao().getAll(), prodQtty);
        ProductCartAdapter productCartAdapter = new ProductCartAdapter(ViewCart.this, productsInCart, prodQtty);
        productCartAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                double subTotal = 0;
                for (int i = 0; i < productsInCart.size(); i++)
                    subTotal += productsInCart.get(i).getProdPrice() * prodQtty.get(i);

                double tax = (subTotal + shipping) * TAX_RATE;
                total = (subTotal + shipping + tax);

                tvSubTotalVal.setText("$" + roundTwoDecimals(subTotal));
                tvShippingVal.setText("$" + roundTwoDecimals(shipping));
                tvTaxesVal.setText("$" + roundTwoDecimals(tax));
                tvTotalVal.setText("$" + roundTwoDecimals(total));



            }
        });
        rvProductsInCart.setAdapter(productCartAdapter);
        productCartAdapter.notifyDataSetChanged();

        btnCancelViewCart.setOnClickListener((View v) -> {
            finish();
        });



        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckOut.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("TOTAL", total);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    public double roundTwoDecimals(double d)
    {
        DecimalFormat twoDForm = new DecimalFormat("#,###,###.00");
        return Double.valueOf(twoDForm.format(d));
    }

}