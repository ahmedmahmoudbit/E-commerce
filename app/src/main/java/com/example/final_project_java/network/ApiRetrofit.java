package com.example.final_project_java.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofit {

    private static Retrofit retrofit = null;

    public static Retrofit getapi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl("http://ecommerce-api.senior-consultingco.com/").
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }


}
