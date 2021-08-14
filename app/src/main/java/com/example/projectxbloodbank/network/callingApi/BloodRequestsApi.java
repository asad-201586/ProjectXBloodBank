package com.example.projectxbloodbank.network.callingApi;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectxbloodbank.R;
import com.example.projectxbloodbank.adapter.BloodRequestsAdapter;
import com.example.projectxbloodbank.model.bloodRequestRes.BloodRequestResponse;
import com.example.projectxbloodbank.model.bloodRequestRes.Datum;
import com.example.projectxbloodbank.network.RetrofitClient;
import com.example.projectxbloodbank.others.CustomLoadingDialog;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BloodRequestsApi {
    private final Context context;
    private final RecyclerView recyclerView;
    private final CustomLoadingDialog loadingDialog;
    private final NavController navController;

    public BloodRequestsApi(Context context, RecyclerView recyclerView,NavController navController) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.navController = navController;
        loadingDialog = new CustomLoadingDialog(context);
    }

    public void bloodRequests(){
        loadingDialog.start();
        Call<BloodRequestResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .BloodRequests();

        call.enqueue(new Callback<BloodRequestResponse>() {
            @Override
            public void onResponse(@NonNull Call<BloodRequestResponse> call, @NonNull Response<BloodRequestResponse> response) {
                loadingDialog.dismiss();
                if (response.isSuccessful()){
                    if (Objects.requireNonNull(response.body()).getStatus()){
                        ArrayList<Datum> list = response.body().getData();
                        BloodRequestsAdapter adapter = new BloodRequestsAdapter(context,list,navController);
                        recyclerView.setAdapter(adapter);
                    }
                }else
                    Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<BloodRequestResponse> call, @NonNull Throwable t) {
                loadingDialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
