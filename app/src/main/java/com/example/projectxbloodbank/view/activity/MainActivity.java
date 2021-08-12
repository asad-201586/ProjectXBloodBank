package com.example.projectxbloodbank.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projectxbloodbank.R;
import com.example.projectxbloodbank.databinding.ActivityMainBinding;
import com.example.projectxbloodbank.others.GlobalMethods;
import com.example.projectxbloodbank.view.fragment.DashboardFragment;
import com.example.projectxbloodbank.view.fragment.HistoryFragment;
import com.example.projectxbloodbank.view.fragment.ProfileFragment;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class MainActivity extends AppCompatActivity implements BubbleNavigationChangeListener{

    private ActivityMainBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = this;
        binding.bubbleNav.setNavigationChangeListener(this);
    }

    @Override
    public void onNavigationChanged(View view, int position) {
        switch (position){
            case 0:
                GlobalMethods.goToOtherFragment(context,new DashboardFragment());
                break;

            case 1:
                GlobalMethods.goToOtherFragment(context,new HistoryFragment());
                break;

            case 2:
                GlobalMethods.goToOtherFragment(context,new ProfileFragment());
                break;
        }
    }
}