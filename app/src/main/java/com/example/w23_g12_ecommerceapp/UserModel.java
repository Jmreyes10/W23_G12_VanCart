package com.example.w23_g12_ecommerceapp;

public class UserModel {

    Integer id;
    String userName;
    String userEmail;
    String userAddress;

    public UserModel(Integer id, String userName, String userEmail, String userAddress) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
    }

    public UserModel(String userName, String userEmail, String userAddress) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
