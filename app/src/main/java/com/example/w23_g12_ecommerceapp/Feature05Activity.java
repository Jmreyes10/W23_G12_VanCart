package com.example.w23_g12_ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Feature05Activity extends AppCompatActivity {

    private RecyclerView faqsRecyclerView;
    private FAQsAdapter faqsAdapter;
    private List<FAQ> faqsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature05);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        faqsList = new ArrayList<>();
        faqsList.add(new FAQ("How do I place an order?", "To place an order, select the product you want to purchase, add it to your cart, and proceed to checkout. Follow the prompts to enter your shipping and payment information to complete the order."));
        faqsList.add(new FAQ("What are the payment options available?", "We accept various payment methods including credit/debit cards, net banking, UPI, digital wallets, and cash on delivery (COD) in select areas."));
        faqsList.add(new FAQ("What is your return policy?", "We have a flexible return policy. If you are not satisfied with your purchase, you can return it within a specified time period for a refund or exchange. The specifics of the return policy will vary depending on the product. To know more about it please contact us."));
        faqsList.add(new FAQ("What is the delivery time?", "The delivery time will depend on the product packing time and your location. We strive to deliver products as quickly as possible, and usually packing time for products would take around 1 hour plus the travel time to reach your location will be your delivery time."));
        faqsList.add(new FAQ("Is my personal and payment information secure?", "Yes, we take the security of your personal and payment information seriously. We use encryption and other security measures to protect your information and ensure safe transactions."));

        faqsRecyclerView = findViewById(R.id.faqsRecyclerView);
        //faqsRecyclerView.addItemDecoration(new SpacingItemDecoration(16));
        faqsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        faqsAdapter = new FAQsAdapter(faqsList);
        faqsRecyclerView.setAdapter(faqsAdapter);



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