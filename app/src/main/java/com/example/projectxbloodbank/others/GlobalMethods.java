package com.example.projectxbloodbank.others;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectxbloodbank.R;
import com.example.projectxbloodbank.view.fragment.HistoryFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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

    public static String getCurrentDate(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        return dateFormat.format(date);
    }

    public static long calculateDays(String lastDonateDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy",Locale.US);
        Date donateDate = null,currentDate = null;
        try{
            donateDate = sdf.parse(lastDonateDate);
            currentDate = sdf.parse(GlobalMethods.getCurrentDate());
        }catch (Exception e){
            e.printStackTrace();
        }

        long diff = Objects.requireNonNull(currentDate).getTime() - Objects.requireNonNull(donateDate).getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
