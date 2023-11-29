package com.base.skillbuilderapi.retrofit;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit;
    public static RetrofitClient retrofitClient;
    public ApiService apiService;
    public static final String BASE_URL = "https://api.example.com/";
    public static synchronized RetrofitClient getInstance(Context context) {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public ApiService getApiService() {
        if (apiService != null) {
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }
}
