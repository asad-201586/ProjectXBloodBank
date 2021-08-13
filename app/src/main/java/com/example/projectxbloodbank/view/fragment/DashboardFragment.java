package com.example.projectxbloodbank.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectxbloodbank.R;
import com.example.projectxbloodbank.databinding.FragmentDashboardBinding;
import com.example.projectxbloodbank.others.GlobalValues;
import com.example.projectxbloodbank.view.activity.MainActivity;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //your code here
        GlobalValues.currentFragment = "dashboard";
        navController = Navigation.findNavController(binding.getRoot());

        binding.layoutEmergency.layoutEmg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_dashboardFragment_to_bloodRequestDetailsFragment);
            }
        });
    }
}