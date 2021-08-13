package com.example.projectxbloodbank.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectxbloodbank.R;
import com.example.projectxbloodbank.databinding.FragmentHistoryBinding;
import com.example.projectxbloodbank.others.GlobalValues;
import com.example.projectxbloodbank.view.activity.MainActivity;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GlobalValues.currentFragment = "history";
    }
}