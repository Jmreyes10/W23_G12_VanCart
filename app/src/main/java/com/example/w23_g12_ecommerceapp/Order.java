package com.example.w23_g12_ecommerceapp;

public class Order {
    private int id;
    private String customerName;
    private String customerAddress;
    private String custumerNumber;
    private String deliveryStatus;

    public Order(int id, String customerName, String customerAddress, String custumerNumber, String deliveryStatus) {
        this.id = id;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.custumerNumber = custumerNumber;
        this.deliveryStatus = deliveryStatus;
    }

//    public Order(int id, String customerName, String customerAddress, String deliveryStatus) {
//        this.id = id;
//        this.customerName = customerName;
//        this.customerAddress = customerAddress;
//        this.deliveryStatus = deliveryStatus;
//    }

    public Order(int id, String customerName, String customerAddress) {
        this.id = id;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public String setDeliveryStatus(String status) {
        return deliveryStatus = status;

    }

    public String getCustumerNumber() {
        return custumerNumber;
    }

    public void setCustumerNumber(String custumerNumber) {
        this.custumerNumber = custumerNumber;
    }
}
