package com.base.skillbuilderapi.retrofit;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit;
    public static RetrofitClient retrofitClient;
    public ApiService apiService;
    public static final String BASE_URL = "https://api.example.com/";
    public RetrofitClient(Context context) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static synchronized RetrofitClient getInstance(Context context) {
        return new RetrofitClient(context);
    }

    public ApiService getApiService() {
        if (apiService == null) {
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }

    public Retrofit getRetrofitInstance() {
        return retrofit;
    }
}
