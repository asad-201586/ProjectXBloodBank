package com.example.projectxbloodbank.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectxbloodbank.R;
import com.example.projectxbloodbank.adapter.BloodRequestsAdapter;
import com.example.projectxbloodbank.databinding.FragmentProfileBinding;
import com.example.projectxbloodbank.model.MyProfileResponse;
import com.example.projectxbloodbank.my_interface.ProfileDataCallBack;
import com.example.projectxbloodbank.network.callingApi.ActivityLogApi;
import com.example.projectxbloodbank.network.callingApi.MyProfileApi;
import com.example.projectxbloodbank.others.GlobalValues;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GlobalValues.currentFragment = "profile";
        getActivityLog();
        getProfileData();
    }

    private void getProfileData() {
        MyProfileApi api = new MyProfileApi(requireContext(), (isDataFound, data) -> {
            if (isDataFound){
                binding.textName.setText(data.getName());
                binding.textAddress.setText(data.getAddress());
                binding.textBloodGroup.setText(data.getBloodGroup());
                binding.textDescription.setText(data.getAboutMe());

                Picasso.get()
                    .load(data.getImage()).fit().centerCrop()
                    .placeholder(R.drawable.simple_man) // placeholder default image
                    .into(binding.imageProfile);
            }
        });
        api.myProfile();
    }

    private void getActivityLog() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        ActivityLogApi api = new ActivityLogApi(requireContext(),binding.recyclerView);
        api.activityLog();
    }
}