package com.example.projectxbloodbank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectxbloodbank.databinding.ItemActivityLogBinding;
import com.example.projectxbloodbank.model.activityLogRes.Datum;

import java.util.ArrayList;

public class ActivityLogAdapter extends RecyclerView.Adapter<ActivityLogAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<Datum> list;

    public ActivityLogAdapter(Context context, ArrayList<Datum> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemActivityLogBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Datum model = list.get(position);

        holder.binding.textDate.setText(model.getDate());
        holder.binding.textTime.setText(model.getTime());
        holder.binding.textAction.setText(model.getAction());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemActivityLogBinding binding;
        public MyViewHolder(ItemActivityLogBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
