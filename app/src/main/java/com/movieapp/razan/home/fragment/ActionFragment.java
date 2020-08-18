package com.movieapp.razan.home.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movieapp.razan.R;

public class ActionFragment extends Fragment {

private RecyclerView recyclerView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=inflater.inflate(R.layout.fragment_action, container, false);
       recyclerView=v.findViewById(R.id.recyclerView_moovies);
    return v;
    }
}