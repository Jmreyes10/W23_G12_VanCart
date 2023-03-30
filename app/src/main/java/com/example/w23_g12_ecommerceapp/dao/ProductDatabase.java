package com.example.w23_g12_ecommerceapp.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.w23_g12_ecommerceapp.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    private static final String DATABASE_NAME = "products_db";
}
