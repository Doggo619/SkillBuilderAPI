package com.base.skillbuilderapi.retrofit;

import com.base.skillbuilderapi.model.elementProgressApi.GetElementProgressOutput;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiService {
    @GET("RZHighlights/getElementProgress.json")
    Call<GetElementProgressOutput> getElementProgressOutput(@Header("Authorization") String token);
}
