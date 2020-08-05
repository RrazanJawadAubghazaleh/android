package com.movieapp.razan.home.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.movieapp.razan.R;
import com.movieapp.razan.home.adapter.RecycleAdapter;
import com.movieapp.razan.home.model.Result;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    HomeViewModel homeViewModel;

    RecycleAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getTrending();
        setUP();
    }

    private void setUP() {

        recyclerView = findViewById(R.id.recyclerView_trending);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

        homeViewModel.listMutableLiveDataTrending.observe(this, new Observer<ArrayList<Result>>() {
            @Override
            public void onChanged(ArrayList<Result> results) {

                recycleAdapter = new RecycleAdapter(HomeActivity.this);

                recycleAdapter.addPlayers(results);
                recyclerView.setAdapter(recycleAdapter);


            }
        });

    }


    @Override
    public void onBackPressed() {
        finish();
    }
}