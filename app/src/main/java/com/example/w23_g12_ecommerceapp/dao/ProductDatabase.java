package com.example.w23_g12_ecommerceapp.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.w23_g12_ecommerceapp.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    private static final String DATABASE_NAME = "products_db";

    public static ProductDatabase INSTANCE;

    public static ProductDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (ProductDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ProductDatabase.class,"products_db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
