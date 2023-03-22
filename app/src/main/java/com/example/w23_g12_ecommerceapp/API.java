package com.example.w23_g12_ecommerceapp;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products/{prodCategory}")
    Call<List<Product>> getProductsByCategory(@Path("prodCategory") String prodCategory);

    @POST("products")
    Call<ResponseBody> addProduct(@Body Product p);

    @PUT("products/{id}")
    Call<ResponseBody> updateProduct(@Path("id") long productId, @Body Product p);

}
