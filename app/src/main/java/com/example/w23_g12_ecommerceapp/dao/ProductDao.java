package com.example.w23_g12_ecommerceapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.w23_g12_ecommerceapp.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products")
    List<Product> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Product> products);

    @Delete
    void deleteProduct(Product product);

    @Query("DELETE FROM products")
    void deleteAll();

    @Query("SELECT COUNT(*) FROM products")
    Integer getRowCount();
}
