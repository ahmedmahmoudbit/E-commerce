package com.example.final_project_java.database.network;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofit {
    private static Retrofit retrofit;
    public static Retrofit getapi() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(chain -> {
                Request request = chain.
                        request()
                        .newBuilder()
                        .addHeader("Accept", "application/json; charset=UTF-8")
                        .addHeader("Content-Type", "application/json; charset=UTF-8")
                        .build();
                return chain.proceed(request);
            });

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://ecommerce-api.senior-consultingco.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit;
    }

}