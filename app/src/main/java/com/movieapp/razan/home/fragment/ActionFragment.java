package com.movieapp.razan.home.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movieapp.razan.R;
import com.movieapp.razan.home.adapter.PagerAdapter;
import com.movieapp.razan.home.adapter.RecycleHomeMoviesAdapter;
import com.movieapp.razan.home.model.ResultPager;
import com.movieapp.razan.home.ui.PageViewModel;

import java.util.ArrayList;

public class ActionFragment extends Fragment {
    private PageViewModel pageViewModel;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView recyclerView;
    private RecycleHomeMoviesAdapter adapter;

    public static ActionFragment newInstance(int index) {
        ActionFragment fragment = new ActionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_action, container, false);
        int genres= PagerAdapter.genres;

        pageViewModel.getMoviesByGenerId(1,genres);

        recyclerView = root.findViewById(R.id.recyclerView_moovies);
        recyclerView.setLayoutManager(new GridLayoutManager((Context) getContext(), 2));

        pageViewModel.resaltMutableLiveData.observe((LifecycleOwner) getContext(), new Observer<ArrayList<ResultPager>>() {
            @Override
            public void onChanged(ArrayList<ResultPager> results) {

                adapter = new RecycleHomeMoviesAdapter(getContext());
                adapter.setList(results);
                recyclerView.setAdapter(adapter);
            }
        });
        return root;
    }



}