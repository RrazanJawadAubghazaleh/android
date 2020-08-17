package com.movieapp.razan.home.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.movieapp.razan.home.fragment.ActionFragment;
import com.movieapp.razan.home.fragment.DarmaFragment;

public class HomeViewOagerAdapter extends FragmentPagerAdapter {
    public HomeViewOagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: ActionFragment actionFragment = new ActionFragment();
                return actionFragment;

            case 1:
                DarmaFragment darmaFragment = new DarmaFragment();
                return darmaFragment;
        }
        return null;

    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                // 4 to chang name  of tab from english to arabic
                return "Action";

            case 1:
                return "darma";

        }
        return null;
    }
}
