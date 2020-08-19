package com.movieapp.razan.home.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movieapp.razan.R;
import com.movieapp.razan.home.adapter.RecycleHomeMoviesAdapter;
import com.movieapp.razan.home.model.Meal;
import com.movieapp.razan.home.model.Result;
import com.movieapp.razan.home.ui.PageViewModel;

import java.util.ArrayList;

public class ActionFragment extends Fragment {
    private PageViewModel pageViewModel;
    private static final String ARG_SECTION_NUMBER = "section_number";

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

    private RecyclerView recyclerView;
    private RecycleHomeMoviesAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_action, container, false);

        final TextView textView = root.findViewById(R.id.textView);
        pageViewModel.getText().observe((LifecycleOwner) getContext(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
pageViewModel.getMealList();

        recyclerView = root.findViewById(R.id.recyclerView_moovies);
        recyclerView.setLayoutManager(new GridLayoutManager((Context) getContext(), 2));
        pageViewModel.mealLiveData.observe((LifecycleOwner) getContext(), new Observer<ArrayList<Meal>>() {
            @Override
            public void onChanged(ArrayList<Meal> meals) {


                adapter = new RecycleHomeMoviesAdapter(getContext());
                adapter.setListMeal(meals);
                recyclerView.setAdapter(adapter);
            }
        });


        return root;
    }


    private ArrayList<Meal> prepareArray() {
        ArrayList<Meal> m = new ArrayList<>();

        Meal p1 = new Meal();
        p1.setMealName("Burger");
        p1.setMealPrice("3");
        m.add(p1);

        p1 = new Meal();
        p1.setMealName("Pizza");
        p1.setMealPrice("4");
        m.add(p1);

        return m;
    }
}