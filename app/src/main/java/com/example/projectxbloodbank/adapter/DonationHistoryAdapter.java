package com.example.projectxbloodbank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectxbloodbank.databinding.ItemDonationHistoryBinding;
import com.example.projectxbloodbank.model.donationHistoryRes.Datum;
import com.example.projectxbloodbank.others.GlobalMethods;

import java.util.ArrayList;

public class DonationHistoryAdapter extends RecyclerView.Adapter<DonationHistoryAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<Datum> list;

    public DonationHistoryAdapter(Context context, ArrayList<Datum> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemDonationHistoryBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Datum model = list.get(position);

        GlobalMethods.setYearMonthDay(model.getDate(),holder.binding.textYear,holder.binding.textMonth,holder.binding.textDay);
        holder.binding.textDonationDuration.setText(String.format("%s Days Ago", GlobalMethods.calculateDays(model.getDate())));
        holder.binding.textBloodGroup.setText(model.getBloodGroup());
        holder.binding.textLocation.setText(model.getLocation());
        holder.binding.textHospital.setText(model.getHospital());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemDonationHistoryBinding binding;
        public MyViewHolder(ItemDonationHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
