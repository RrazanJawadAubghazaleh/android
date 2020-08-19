package com.movieapp.razan.home.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.movieapp.razan.R;
import com.movieapp.razan.databinding.ActivityHomeBandingBinding;
import com.movieapp.razan.home.adapter.HomeViewOagerAdapter;
import com.movieapp.razan.home.adapter.PagerAdapter;
import com.movieapp.razan.home.adapter.RecycleAdapter;
import com.movieapp.razan.home.adapter.TrandingRecycleAdapter;
import com.movieapp.razan.home.model.Genre;
import com.movieapp.razan.home.model.Result;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViewPager viewPager;
    private HomeViewOagerAdapter homeViewOagerAdapter;
    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;

private RecycleAdapter recycleAdapter;
    HomeViewModel homeViewModel;
    ActivityHomeBandingBinding homeBanding;
    TrandingRecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       // homeBanding = DataBindingUtil.setContentView(this, R.layout.activity_home);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,
//          /      LinearLayoutManager.HORIZONTAL, false));
//        recyclerView.setHasFixedSize(true);


        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getTrending();
        homeViewModel.getGenersList();
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
                recycleAdapter.setList(results);
                //adapter = new TrandingRecycleAdapter(HomeActivity.this, results);

                 recyclerView.setAdapter(recycleAdapter);
               // recyclerView.setAdapter(adapter);


            }
        });


      homeViewModel.genersListMutableLiveData.observe(this, new Observer< ArrayList<Genre>>() {
          @Override
          public void onChanged( ArrayList<Genre> genres) {

              mPager = findViewById(R.id.view_pager);
              pagerAdapter = new PagerAdapter(HomeActivity.this, getSupportFragmentManager(),genres);
              mPager.setAdapter(pagerAdapter);
              SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
              viewPagerTab.setViewPager(mPager);
          }
      });
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}