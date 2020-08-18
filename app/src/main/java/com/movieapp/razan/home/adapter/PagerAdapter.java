package com.movieapp.razan.home.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.movieapp.razan.home.fragment.ActionFragment;
import com.movieapp.razan.home.ui.HomeActivity;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public PagerAdapter(@NonNull FragmentManager fm,int behavior,Context context) {
        super(fm, behavior);
        this.context = context;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new ActionFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                // 4 to chang name  of tab from english to arabic
                return "Action";

            case 1:
                return "Darama";

        }
        return null;
    }
}
