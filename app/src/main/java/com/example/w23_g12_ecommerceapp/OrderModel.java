package com.example.w23_g12_ecommerceapp;

public class OrderModel {
    Integer id;
    String prodName;
    String prodQuantity;
    String prodPrice;

    public OrderModel(String prodName, String prodQuantity, String prodPrice) {
        this.prodName = prodName;
        this.prodQuantity = prodQuantity;
        this.prodPrice = prodPrice;
    }

    public OrderModel(Integer id, String prodName, String prodQuantity, String prodPrice) {
        this.id = id;
        this.prodName = prodName;
        this.prodQuantity = prodQuantity;
        this.prodPrice = prodPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(String prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }
}
