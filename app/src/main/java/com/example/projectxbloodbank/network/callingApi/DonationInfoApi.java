package com.example.projectxbloodbank.network.callingApi;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.projectxbloodbank.R;
import com.example.projectxbloodbank.model.DonationInfoResponse;
import com.example.projectxbloodbank.network.RetrofitClient;
import com.example.projectxbloodbank.others.CustomLoadingDialog;
import com.example.projectxbloodbank.others.GlobalMethods;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonationInfoApi {
    private final Context context;
    private final CustomLoadingDialog loadingDialog;
    private final TextView textView;
    private final TextView textYouCanDonate;

    public DonationInfoApi(Context context, TextView textView,TextView textYouCanDonate) {
        this.context = context;
        this.textView = textView;
        this.textYouCanDonate = textYouCanDonate;
        loadingDialog = new CustomLoadingDialog(context);
    }

    public void donationInfo(){
        loadingDialog.start();
        Call<DonationInfoResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .DonationInfo();

        call.enqueue(new Callback<DonationInfoResponse>() {
            @Override
            public void onResponse(@NonNull Call<DonationInfoResponse> call, @NonNull Response<DonationInfoResponse> response) {
                loadingDialog.dismiss();
                if (response.isSuccessful()){
                    long days = GlobalMethods.calculateDays(Objects.requireNonNull(response.body()).getLastDonatedAt());
                    textView.setText(String.valueOf(days));
                    canIDonate(days,textYouCanDonate);
                }else Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<DonationInfoResponse> call, @NonNull Throwable t) {
                loadingDialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void canIDonate(long days,TextView textYouCanDonate) {
        if (days>90) textYouCanDonate.setText(R.string.you_can_donate_now); // 3 months
        else textYouCanDonate.setText(R.string.you_cant_donate_now);
    }

}
