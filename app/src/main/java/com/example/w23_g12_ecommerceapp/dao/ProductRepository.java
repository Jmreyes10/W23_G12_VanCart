package com.example.w23_g12_ecommerceapp.dao;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.w23_g12_ecommerceapp.Product;

import java.util.List;

public class ProductRepository {

    ProductDao productDao;

    ProductRepository(Application application){
        ProductDatabase productDatabase = ProductDatabase.getDatabase(application);
        productDao = productDatabase.productDao();
    }

    LiveData<List<Product>> getOrders(){
        return productDao.getAllOrders();
    }

    void insert(Product product){
        new insertAsyncTask(productDao).execute(product);
    }
    private static class insertAsyncTask extends AsyncTask<Product, Void, Void>{
        private ProductDao dao;

        insertAsyncTask(ProductDao productDao){
            dao = productDao;
        }


        @Override
        protected Void doInBackground(Product... products) {
            dao.insert((List<Product>) products[0]);
            return null;
        }
    }
}
