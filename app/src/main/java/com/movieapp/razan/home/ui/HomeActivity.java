package com.movieapp.razan.home.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.movieapp.razan.R;
import com.movieapp.razan.databinding.ActivityHomeBandingBinding;
import com.movieapp.razan.home.adapter.RecycleAdapter;
import com.movieapp.razan.home.adapter.TrandingRecycleAdapter;
import com.movieapp.razan.home.model.Result;
import com.movieapp.razan.login.viewModel.LoginViewModel;
import com.movieapp.razan.login.viewModel.LoginViewModelFactory;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    HomeViewModel homeViewModel;
    RecycleAdapter recycleAdapter;

    ActivityHomeBandingBinding homeBanding;
    TrandingRecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeBanding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        homeBanding.recyclerViewTrending.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        homeBanding.recyclerViewTrending.setHasFixedSize(true);


        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getTrending();
        setUP();
    }

    private void setUP() {

        /*recyclerView = findViewById(R.id.recyclerView_trending);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));*/

        homeViewModel.listMutableLiveDataTrending.observe(this, new Observer<ArrayList<Result>>() {
            @Override
            public void onChanged(ArrayList<Result> results) {

                //recycleAdapter = new RecycleAdapter(HomeActivity.this);
                adapter=new TrandingRecycleAdapter(HomeActivity.this,results);

              //  recycleAdapter.addPlayers(results);
               // recyclerView.setAdapter(recycleAdapter);
                homeBanding.recyclerViewTrending.setAdapter(adapter);


            }
        });

    }


    @Override
    public void onBackPressed() {
        finish();
    }
}