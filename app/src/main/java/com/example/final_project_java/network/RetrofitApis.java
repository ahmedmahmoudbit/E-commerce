package com.example.final_project_java.network;

import com.example.final_project_java.activity.carts.add_cart.AddToCartRequest;
import com.example.final_project_java.activity.carts.add_cart.AddToCartResponse;
import com.example.final_project_java.activity.carts.show_cart.ShowItemCardResponse;
import com.example.final_project_java.activity.lasts_product.LastsResponse;
import com.example.final_project_java.activity.login.LoginRequest;
import com.example.final_project_java.activity.login.LoginResponse;
import com.example.final_project_java.activity.logoutAndmore.LogoutResponse;
import com.example.final_project_java.activity.register.RegisterRequest;
import com.example.final_project_java.activity.register.RegisterResponse;
import com.example.final_project_java.activity.search.ProductResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitApis {

    @POST("api/register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @POST("api/login")
    Call<LoginResponse> login (@Body LoginRequest loginRequest);

    @GET("api/categories")
    Call<LogoutResponse> logout (@Header("Authorization") String token);

    @GET("api/products")
    Call<ProductResponse> product ();

    @GET("api/latest-product")
    Call<LastsResponse> lasts ();

    @POST("api/add-to-cart")
    Call<AddToCartResponse> add_to_cart (@Header("Authorization") String token , AddToCartRequest addToCartRequest);

    @GET("api/cart")
    Call<ShowItemCardResponse> carts_show (@Header("Authorization") String token);

}
