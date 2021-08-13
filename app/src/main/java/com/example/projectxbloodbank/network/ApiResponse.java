package com.example.projectxbloodbank.network;

import com.example.projectxbloodbank.model.bloodRequestRes.BloodRequestResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiResponse {

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/b/P3UI")
    Call<BloodRequestResponse> BloodRequests();

}
