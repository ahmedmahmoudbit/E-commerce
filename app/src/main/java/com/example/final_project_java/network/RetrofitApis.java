package com.example.final_project_java.network;

import com.example.final_project_java.activity.carts.add_cart.add.AddResponse;
import com.example.final_project_java.activity.carts.add_cart.addCartItem.AddToCartItemsResponse;
import com.example.final_project_java.activity.carts.add_cart.addCartItem.AddToCartRequest;
import com.example.final_project_java.activity.carts.add_cart.remov.RemoveResponse;
import com.example.final_project_java.activity.carts.add_cart.showCartItems.ShowCartItemsResponse;
import com.example.final_project_java.activity.carts.add_cart.sub.SubResponse;
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
import retrofit2.http.Path;

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
    Call<AddToCartItemsResponse> add_to_cart (@Header("Authorization") String token ,@Body AddToCartRequest addToCartRequest);

    @GET("api/cart")
    Call<ShowCartItemsResponse> carts_show (@Header("Authorization") String token);


    @GET("api/add-qty/{item_id}")
    Call<AddResponse> addItem(@Path("item_id") int itemId,
                                     @Header("Authorization")String token);

    @GET("api/sub-qty/{item_id}")
    Call<SubResponse> subItem(@Path("item_id") int itemId,
                              @Header("Authorization")String token);

    @POST("api/remove-item/{item_id}")
    Call<RemoveResponse> removeItem(@Path("item_id") int itemId,
                                    @Header("Authorization")String token);


}
