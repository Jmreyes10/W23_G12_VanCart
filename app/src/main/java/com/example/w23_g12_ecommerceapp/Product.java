package com.example.w23_g12_ecommerceapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Product {

    @PrimaryKey
    private long id;

    @ColumnInfo(name="prod_code")
    private String prodCode;

    @ColumnInfo(name = "prod_name")
    private String prodName;

    @ColumnInfo(name = "prod_category")
    private String prodCategory;

    @ColumnInfo(name = "prod_imgurl")
    private String prodImgUrl;

    @ColumnInfo(name = "product_price")
    private double prodPrice;

    public Product() {
    }

    public Product(long id, String prodCode, String prodName, String prodCategory, String prodImgUrl, double prodPrice) {
        this.id=id;
        this.prodCode = prodCode;
        this.prodName = prodName;
        this.prodCategory = prodCategory;
        this.prodImgUrl = prodImgUrl;
        this.prodPrice = prodPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdCategory() {
        return prodCategory;
    }

    public void setProdCategory(String prodCategory) {
        this.prodCategory = prodCategory;
    }

    public String getProdImgUrl() {
        return prodImgUrl;
    }

    public void setProdImgUrl(String prodImgUrl) {
        this.prodImgUrl = prodImgUrl;
    }

    public double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(double prodPrice) {
        this.prodPrice = prodPrice;
    }

}
