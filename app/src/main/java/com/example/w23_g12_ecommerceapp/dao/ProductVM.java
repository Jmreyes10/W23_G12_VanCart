package com.example.w23_g12_ecommerceapp.dao;

import android.app.Application;
import android.app.Person;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.w23_g12_ecommerceapp.Product;

import java.util.List;

public class ProductVM extends AndroidViewModel {
    ProductRepository repository;
    LiveData<List<Product>> orderList;

    public ProductVM(Application application) {
        super(application);
        repository = new ProductRepository(application);
        orderList = repository.getOrders();
    }

    public LiveData<List<Product>> getOrderList(){
        return orderList;
    }

    public void insertOrder(Product product){
        repository.insert(product);
    }
}
