package com.example.projectxbloodbank.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectxbloodbank.R;
import com.example.projectxbloodbank.databinding.FragmentBloodRequestDetailsBinding;
import com.example.projectxbloodbank.model.bloodRequestRes.Datum;
import com.example.projectxbloodbank.others.GlobalValues;
import com.squareup.picasso.Picasso;

public class BloodRequestDetailsFragment extends Fragment {

    private FragmentBloodRequestDetailsBinding binding;
    private Datum model;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBloodRequestDetailsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GlobalValues.currentFragment = "blood_request_details";
        model = (Datum) requireArguments().getSerializable("model");
        setData();
    }

    private void setData() {
        binding.textName.setText(model.getName());
        binding.textBloodGroup.setText(model.getBloodGroup());
        binding.textLocation.setText(model.getLocation());
        binding.textHospital.setText(model.getHospital());
        binding.textDescription.setText(model.getDescription());
        binding.textPhoneNumber.setText(model.getPhone());

        Picasso.get()
                .load(model.getImage()).fit().centerCrop()
                .placeholder(R.drawable.simple_man) // placeholder default image
                .into(binding.imageProfile);
    }
}