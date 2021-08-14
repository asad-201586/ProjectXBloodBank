package com.example.projectxbloodbank.network.callingApi;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectxbloodbank.R;
import com.example.projectxbloodbank.adapter.ActivityLogAdapter;
import com.example.projectxbloodbank.model.activityLogRes.ActivityLogResponse;
import com.example.projectxbloodbank.model.activityLogRes.Datum;
import com.example.projectxbloodbank.network.RetrofitClient;
import com.example.projectxbloodbank.others.CustomLoadingDialog;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogApi {
    private final Context context;
    private final RecyclerView recyclerView;
    private final CustomLoadingDialog loadingDialog;

    public ActivityLogApi(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        loadingDialog = new CustomLoadingDialog(context);
    }

    public void activityLog(){
        loadingDialog.start();
        Call<ActivityLogResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .ActivityLog();

        call.enqueue(new Callback<ActivityLogResponse>() {
            @Override
            public void onResponse(@NonNull Call<ActivityLogResponse> call, @NonNull Response<ActivityLogResponse> response) {
                loadingDialog.dismiss();
                if (response.isSuccessful()){
                    if (Objects.requireNonNull(response.body()).getStatus()){
                        ArrayList<Datum> list = response.body().getData();
                        ActivityLogAdapter adapter = new ActivityLogAdapter(context,list);
                        recyclerView.setAdapter(adapter);
                    }
                }else Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<ActivityLogResponse> call, @NonNull Throwable t) {

            }
        });
    }
}
