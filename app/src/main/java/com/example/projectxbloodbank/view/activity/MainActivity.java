package com.example.projectxbloodbank.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.example.projectxbloodbank.R;
import com.example.projectxbloodbank.databinding.ActivityMainBinding;
import com.example.projectxbloodbank.others.GlobalValues;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements BubbleNavigationChangeListener{

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUpNavigation();
        binding.bubbleNav.setNavigationChangeListener(this);
    }

    private void setUpNavigation() {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        navController = Navigation.findNavController(this,R.id.my_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, binding.drawerLayout);
    }

    @Override
    public void onNavigationChanged(View view, int position) {
        selectFrag(position);
    }

    private void selectFrag(int position) {
        switch (GlobalValues.currentFragment) {
            case "dashboard":
                if (position == 1)
                    navController.navigate(R.id.action_dashboardFragment_to_historyFragment);
                else navController.navigate(R.id.action_dashboardFragment_to_profileFragment);
                break;
            case "history":
                if (position == 0)
                    navController.navigate(R.id.action_historyFragment_to_dashboardFragment);
                else navController.navigate(R.id.action_historyFragment_to_profileFragment);
                break;
            case "profile":
                if (position == 1)
                    navController.navigate(R.id.action_profileFragment_to_historyFragment);
                else navController.navigate(R.id.action_profileFragment_to_dashboardFragment);
                break;
        }

    }
}