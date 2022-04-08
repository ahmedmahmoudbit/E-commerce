package com.example.final_project_java.database.network;

import com.example.final_project_java.activity.activities.product.data.ProductResponse;
import com.example.final_project_java.activity.carts.cart_page.addCartItem.data.AddToCartItemsResponse;
import com.example.final_project_java.activity.carts.cart_page.data.PlaceOrderResponse;
import com.example.final_project_java.activity.carts.cart_page.operations.add.AddResponse;
import com.example.final_project_java.activity.carts.cart_page.addCartItem.AddToCartRequest;
import com.example.final_project_java.activity.carts.cart_page.operations.remov.RemoveResponse;
import com.example.final_project_java.activity.carts.cart_page.showCartItems.ShowCartItemsResponse;
import com.example.final_project_java.activity.carts.cart_page.operations.sub.SubResponse;
import com.example.final_project_java.activity.carts.cheackout.data.PlaceOrderRequest;
import com.example.final_project_java.activity.lasts_product.LastsResponse;
import com.example.final_project_java.activity.login.LoginRequest;
import com.example.final_project_java.activity.login.LoginResponse;
import com.example.final_project_java.activity.more.logout.data.LogoutResponse;
import com.example.final_project_java.activity.passeord_reset.data.ForgotPasswordResponse;
import com.example.final_project_java.activity.register.RegisterRequest;
import com.example.final_project_java.activity.register.RegisterResponse;
import com.example.final_project_java.activity.search.data.SearchResponses;
import com.example.final_project_java.activity.setting.change_password.data.ChangePasswordRequest;
import com.example.final_project_java.activity.setting.change_password.data.ChangePasswordResponse;
import com.example.final_project_java.activity.setting.profile.data.ProfileResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApis {

    @POST("api/register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @POST("api/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("api/categories")
    Call<LogoutResponse> logout(@Header("Authorization") String token);

    @GET("api/profile/{id}")
    Call<ProfileResponse> profile(@Header("Authorization") String token, @Path("id") int id);

    @GET("api/products")
    Call<ProductResponse> product();

    @POST("api/forgot-password")
        // from postman is = api/forgot-password?email=###
    Call<ForgotPasswordResponse> forgot_password(@Query("email") String emil);

    @GET("api/api/search")
    @FormUrlEncoded
        // add simple Request
    Call<SearchResponses> search(@Field("product_id") String search);

    @GET("api/latest-product")
    Call<LastsResponse> lasts();

    @POST("api/place-order")
    Call<PlaceOrderResponse> addPlaceOrder(@Body PlaceOrderRequest placeOrderRequest, @Header("Authorization") String token);


    @POST("api/add-to-cart")
    Call<AddToCartItemsResponse> add_to_cart(@Header("Authorization") String token, @Body AddToCartRequest addToCartRequest);

    @POST("api/change-password")
    Call<ChangePasswordResponse> change_password(@Header("Authorization") String token, @Body ChangePasswordRequest request);

    @GET("api/cart")
    Call<ShowCartItemsResponse> carts_show(@Header("Authorization") String token);


    @GET("api/add-qty/{item_id}")
    Call<AddResponse> addItem(@Path("item_id") int itemId, @Header("Authorization") String token);


    @GET("api/sub-qty/{item_id}")
    Call<SubResponse> subItem(@Path("item_id") int itemId, @Header("Authorization") String token);


    @POST("api/remove-item/{item_id}")
        // from postman is = api/sub-qty/1
    Call<RemoveResponse> removeItem(@Path("item_id") int itemId, @Header("Authorization") String token);


}
