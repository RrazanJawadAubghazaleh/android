package com.movieapp.razan.home.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.movieapp.razan.home.fragment.ActionFragment;

import com.movieapp.razan.home.model.Genre;
import java.util.ArrayList;


public class PagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    ArrayList<Genre> items;

    public PagerAdapter(Context context, FragmentManager fm, ArrayList<Genre> list) {
        super(fm);
        this.context = context;
        this.items = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new ActionFragment();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public CharSequence getPageTitle(int position) {

         return items.get(position).getName().toString();

    }



}
