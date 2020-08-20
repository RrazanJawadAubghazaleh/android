package com.movieapp.razan.home.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.movieapp.razan.R;
import com.movieapp.razan.databinding.ActivityHomeBandingBinding;
import com.movieapp.razan.home.adapter.PagerAdapter;
import com.movieapp.razan.home.adapter.RecycleAdapter;
import com.movieapp.razan.home.adapter.RecycleHomeMoviesAdapter;
import com.movieapp.razan.home.adapter.TrandingRecycleAdapter;
import com.movieapp.razan.home.model.Genre;
import com.movieapp.razan.home.model.Result;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    private com.movieapp.razan.home.adapter.RecycleHomeMoviesAdapter recycleHomeMoviesAdapter;

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
        recycleHomeMoviesAdapter=new RecycleHomeMoviesAdapter(HomeActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recycleHomeMoviesAdapter.getFilter();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}