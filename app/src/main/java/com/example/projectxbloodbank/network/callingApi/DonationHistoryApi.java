package com.example.projectxbloodbank.network.callingApi;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectxbloodbank.R;
import com.example.projectxbloodbank.adapter.DonationHistoryAdapter;
import com.example.projectxbloodbank.model.donationHistoryRes.Datum;
import com.example.projectxbloodbank.model.donationHistoryRes.DonationHistoryResponse;
import com.example.projectxbloodbank.network.RetrofitClient;
import com.example.projectxbloodbank.others.CustomLoadingDialog;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonationHistoryApi {
    private final Context context;
    private final RecyclerView recyclerView;
    private final String type;
    private final CustomLoadingDialog loadingDialog;

    public DonationHistoryApi(Context context, RecyclerView recyclerView,String type) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.type = type;
        loadingDialog = new CustomLoadingDialog(context);
    }

    public void donationHistory(){
        loadingDialog.start();

        Call<DonationHistoryResponse> call;
        if (type.equals("donation")){
            call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .DonationHistory();
        }else {
            call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .ServiceTakenHistory();
        }

        call.enqueue(new Callback<DonationHistoryResponse>() {
            @Override
            public void onResponse(@NonNull Call<DonationHistoryResponse> call, @NonNull Response<DonationHistoryResponse> response) {
                loadingDialog.dismiss();
                if (response.isSuccessful()){
                    if (Objects.requireNonNull(response.body()).getStatus()){
                        ArrayList<Datum> list = response.body().getData().getData();
                        DonationHistoryAdapter adapter = new DonationHistoryAdapter(context,list);
                        recyclerView.setAdapter(adapter);
                    }
                }else Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<DonationHistoryResponse> call, @NonNull Throwable t) {
                loadingDialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
