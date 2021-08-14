package com.example.projectxbloodbank.network;

import com.example.projectxbloodbank.model.DonationInfoResponse;
import com.example.projectxbloodbank.model.MyProfileResponse;
import com.example.projectxbloodbank.model.activityLogRes.ActivityLogResponse;
import com.example.projectxbloodbank.model.bloodRequestRes.BloodRequestResponse;
import com.example.projectxbloodbank.model.donationHistoryRes.DonationHistoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiResponse {

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/b/P3UI")
    Call<BloodRequestResponse> BloodRequests();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/b/OI73")
    Call<DonationInfoResponse> DonationInfo();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/b/YTAV")
    Call<DonationHistoryResponse> DonationHistory();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/b/TQEL")
    Call<DonationHistoryResponse> ServiceTakenHistory();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/b/7TGG")
    Call<ActivityLogResponse> ActivityLog();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/b/U074")
    Call<MyProfileResponse> MyProfile();





}
