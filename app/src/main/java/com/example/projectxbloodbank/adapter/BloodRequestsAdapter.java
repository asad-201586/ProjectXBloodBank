package com.example.projectxbloodbank.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectxbloodbank.R;
import com.example.projectxbloodbank.databinding.ItemBloodRequestEmergencyBinding;
import com.example.projectxbloodbank.databinding.ItemBloodRequestNormalBinding;
import com.example.projectxbloodbank.model.bloodRequestRes.Datum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BloodRequestsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final ArrayList<Datum> dataList;
    private final int VIEW_NORMAL = 0;
    private int VIEW_EMERGENCY = 1;
    private final NavController navController;

    public BloodRequestsAdapter(Context context, ArrayList<Datum> dataList,NavController navController) {
        this.context = context;
        this.dataList = dataList;
        this.navController = navController;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_NORMAL)
            return new BloodRequestViewHolderNormal(ItemBloodRequestNormalBinding.inflate(LayoutInflater.from(context),parent,false));
        return new BloodRequestViewHolderEmergency(ItemBloodRequestEmergencyBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Datum model = dataList.get(position);
        if (dataList.get(position).getIsEmergency()){
            ((BloodRequestViewHolderEmergency)holder).emergencyBinding.textName.setText(model.getName());
            ((BloodRequestViewHolderEmergency)holder).emergencyBinding.textBloodGroup.setText(model.getBloodGroup());
            ((BloodRequestViewHolderEmergency)holder).emergencyBinding.textHospital.setText(model.getHospital());
            ((BloodRequestViewHolderEmergency)holder).emergencyBinding.textLocation.setText(model.getLocation());

            Picasso.get()
                    .load(model.getImage()).fit().centerCrop()
                    .placeholder(R.drawable.simple_man) // placeholder default image
                    .into(((BloodRequestViewHolderEmergency)holder).emergencyBinding.imageProfile);

        }else {
            ((BloodRequestViewHolderNormal)holder).normalBinding.textName.setText(model.getName());
            ((BloodRequestViewHolderNormal)holder).normalBinding.textBloodGroup.setText(model.getBloodGroup());
            ((BloodRequestViewHolderNormal)holder).normalBinding.textHospital.setText(model.getHospital());
            ((BloodRequestViewHolderNormal)holder).normalBinding.textLocation.setText(model.getLocation());

            Picasso.get()
                    .load(model.getImage()).fit().centerCrop()
                    .placeholder(R.drawable.simple_man) // placeholder default image
                    .into(((BloodRequestViewHolderNormal)holder).normalBinding.imageProfile);
        }

        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("model",model);
            navController.navigate(R.id.action_dashboardFragment_to_bloodRequestDetailsFragment,bundle);
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class BloodRequestViewHolderNormal extends RecyclerView.ViewHolder {
        public final ItemBloodRequestNormalBinding normalBinding;
        public BloodRequestViewHolderNormal(ItemBloodRequestNormalBinding normalBinding) {
            super(normalBinding.getRoot());
            this.normalBinding = normalBinding;
        }
    }

    public static class BloodRequestViewHolderEmergency extends RecyclerView.ViewHolder {
        public final ItemBloodRequestEmergencyBinding emergencyBinding;
        public BloodRequestViewHolderEmergency(ItemBloodRequestEmergencyBinding emergencyBinding) {
            super(emergencyBinding.getRoot());
            this.emergencyBinding = emergencyBinding;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position).getIsEmergency())
            return VIEW_EMERGENCY;
        return VIEW_NORMAL;
    }
}
