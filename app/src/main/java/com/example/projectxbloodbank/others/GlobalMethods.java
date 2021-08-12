package com.example.projectxbloodbank.others;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectxbloodbank.R;
import com.example.projectxbloodbank.view.fragment.HistoryFragment;

public class GlobalMethods {
    public static boolean goToOtherFragment(Context c, Fragment fragment) {
        if (fragment != null) {

            ((FragmentActivity)c).getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
