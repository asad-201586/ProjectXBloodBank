package com.example.projectxbloodbank.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectxbloodbank.databinding.FragmentHistoryBinding;
import com.example.projectxbloodbank.network.callingApi.DonationHistoryApi;
import com.example.projectxbloodbank.others.GlobalValues;

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

        binding.layoutDonations.setOnClickListener(v -> {
            if (binding.textDonations.getTag().equals("donations")) {
                binding.textDonations.setVisibility(View.GONE);
                binding.textDonationsZoom.setVisibility(View.VISIBLE);
                binding.textServiceTaken.setVisibility(View.VISIBLE);
                binding.textServiceTakenZoom.setVisibility(View.GONE);
                binding.textDonations.setTag("donations_zoom");
                binding.textServiceTaken.setTag("service");
            }
        });

        binding.layoutServiceTaken.setOnClickListener(v -> {
            if (binding.textServiceTaken.getTag().equals("service")) {
                binding.textDonations.setVisibility(View.VISIBLE);
                binding.textDonationsZoom.setVisibility(View.GONE);
                binding.textServiceTaken.setVisibility(View.GONE);
                binding.textServiceTakenZoom.setVisibility(View.VISIBLE);
                binding.textDonations.setTag("donations");
                binding.textServiceTaken.setTag("service_zoom");
            }
        });

        getDonationHistory();
    }

    private void getDonationHistory() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        DonationHistoryApi api = new DonationHistoryApi(requireContext(),binding.recyclerView);
        api.donationHistory();
    }
}