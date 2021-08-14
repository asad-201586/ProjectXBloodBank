package com.example.projectxbloodbank.network.callingApi;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.projectxbloodbank.R;
import com.example.projectxbloodbank.model.MyProfileResponse;
import com.example.projectxbloodbank.my_interface.ProfileDataCallBack;
import com.example.projectxbloodbank.network.RetrofitClient;
import com.example.projectxbloodbank.others.CustomLoadingDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileApi {
    private final Context context;
    private final CustomLoadingDialog loadingDialog;
    private final ProfileDataCallBack callBack;

    public MyProfileApi(Context context,ProfileDataCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        loadingDialog = new CustomLoadingDialog(context);
    }

    public void myProfile(){
        loadingDialog.start();
        Call<MyProfileResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .MyProfile();

        call.enqueue(new Callback<MyProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<MyProfileResponse> call, @NonNull Response<MyProfileResponse> response) {
                loadingDialog.dismiss();
                if (response.isSuccessful()){
                    callBack.profileData(true,response.body());
                }else {
                    callBack.profileData(false,null);
                    Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MyProfileResponse> call, @NonNull Throwable t) {
                loadingDialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                callBack.profileData(false,null);
            }
        });
    }
}
