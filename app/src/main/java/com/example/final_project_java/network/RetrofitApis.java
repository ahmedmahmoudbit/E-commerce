package com.example.final_project_java.network;

import com.example.final_project_java.activity.carts.add_cart.AddToCartResponse;
import com.example.final_project_java.activity.carts.show_cart.ShowItemCardResponse;
import com.example.final_project_java.activity.lasts_product.LastsResponse;
import com.example.final_project_java.activity.login.LoginRequest;
import com.example.final_project_java.activity.login.LoginResponse;
import com.example.final_project_java.activity.logoutAndmore.LogoutResponse;
import com.example.final_project_java.activity.register.RegisterRequest;
import com.example.final_project_java.activity.register.RegisterResponse;
import com.example.final_project_java.activity.search.SearchRequest;
import com.example.final_project_java.activity.search.SearchResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @GET("api/search")
    Call<SearchResponse> search (@Body SearchRequest searchRequest);

    @GET("api/latest-product")
    Call<LastsResponse> lasts ();

    @POST("api/add-to-cart")
    @FormUrlEncoded
    Call<AddToCartResponse> add_to_cart (@Header("Authorization") String token , @Field("product_id") String id);

    @GET("api/cart")
    Call<ShowItemCardResponse> carts_show (@Header("Authorization") String token);


}
