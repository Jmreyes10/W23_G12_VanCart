package com.example.w23_g12_ecommerceapp;

public class CustomerRates {
    private int transactionId;
    private String reviewDate;
    private float rateQ1;
    private float rateQ2;
    private float rateQ3;
    private float rateQ4;

    public CustomerRates() {
    }

    public CustomerRates(int transactionId, String reviewDate, float rateQ1, float rateQ2, float rateQ3, float rateQ4) {
        this.transactionId = transactionId;
        this.reviewDate = reviewDate;
        this.rateQ1 = rateQ1;
        this.rateQ2 = rateQ2;
        this.rateQ3 = rateQ3;
        this.rateQ4 = rateQ4;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public float getRateQ1() {
        return rateQ1;
    }

    public void setRateQ1(float rateQ1) {
        this.rateQ1 = rateQ1;
    }

    public float getRateQ2() {
        return rateQ2;
    }

    public void setRateQ2(float rateQ2) {
        this.rateQ2 = rateQ2;
    }

    public float getRateQ3() {
        return rateQ3;
    }

    public void setRateQ3(float rateQ3) {
        this.rateQ3 = rateQ3;
    }

    public float getRateQ4() {
        return rateQ4;
    }

    public void setRateQ4(float rateQ4) {
        this.rateQ4 = rateQ4;
    }
}
