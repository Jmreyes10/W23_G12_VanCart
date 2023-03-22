package com.example.w23_g12_ecommerceapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewCart extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        ArrayList<Product> productsInCart = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        int numItems = productsInCart.size();
        long[] prodsId = new long[numItems];
        String[] prodsCode = new String[numItems];
        String[] prodsName = new String[numItems];
        String[] prodCategory = new String[numItems];
        String[] prodImgUrl = new String[numItems];
        double[] prodsPrice = new double[numItems];

        numItems = bundle.getInt("numItems");
        prodsId = bundle.getLongArray("prodsId");
        prodsCode= bundle.getStringArray("prodsCode");
        prodsName= bundle.getStringArray("prodsName");
        prodCategory= bundle.getStringArray("prodCategory");
        prodImgUrl= bundle.getStringArray("prodImgUrl");
        prodsPrice = bundle.getDoubleArray("prodsPrice");

        for(int i=0;i<numItems;i++){
            productsInCart.add(new Product(prodsId[i], prodsCode[i], prodsName[i], prodCategory[i], prodImgUrl[i], prodsPrice[i]));
        }

        textView = findViewById(R.id.textView);
        textView.setText(productsInCart.get(0).getProdName().toString());
    }
}