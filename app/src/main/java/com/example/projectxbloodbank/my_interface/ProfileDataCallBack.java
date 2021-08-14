package com.example.projectxbloodbank.my_interface;

import com.example.projectxbloodbank.model.MyProfileResponse;

public interface ProfileDataCallBack {
    public void profileData(boolean isDataFound, MyProfileResponse data);
}
