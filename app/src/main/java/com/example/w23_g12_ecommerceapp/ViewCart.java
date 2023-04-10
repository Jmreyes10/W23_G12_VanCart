package com.example.w23_g12_ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.w23_g12_ecommerceapp.dao.ProductDatabase;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

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

    PaymentSheet paymentSheet;
    String paymentIntentClientSecret;
    PaymentSheet.CustomerConfiguration customerConfig;

    String valueToPass;


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

        valueToPass = String.valueOf((int)Math.round(total));
        paymentSheet = new PaymentSheet(this, this::onPaymentSheetResult);


        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDetails();
            }
        });

    }

    public double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#,###,###.00");
        return Double.valueOf(twoDForm.format(d));
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
                    Toast.makeText(ViewCart.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
            for (int i=0; i < productsInCart.size(); i++) {
                OrderHistoryDBHelper historyDBHelper = new OrderHistoryDBHelper(ViewCart.this);
                OrderModel orderModel = new OrderModel(productsInCart.get(i).getProdName(), prodQtty.get(i).toString(), String.valueOf(productsInCart.get(i).getProdPrice()));
                historyDBHelper.addOrder(orderModel);
            }
            Toast.makeText(this, "Completed", Toast.LENGTH_SHORT).show();        }
    }

}