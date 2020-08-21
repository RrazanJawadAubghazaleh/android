package com.movieapp.razan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.movieapp.razan.databinding.ActivityDetailsBinding;
import com.movieapp.razan.home.adapter.RecycleStarCastAdapter;
import com.movieapp.razan.home.model.Cast;
import com.movieapp.razan.home.model.DetailsMovieModel;
import com.movieapp.razan.home.ui.HomeActivity;
import com.movieapp.razan.home.ui.viewmodels.DetailsViewModel;
import com.movieapp.razan.home.ui.viewmodels.HomeViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    DetailsViewModel detailsViewModel;
    RecycleStarCastAdapter recycleStarCastAdapter;
    int numberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_details);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        numberId = getIntent().getExtras().getInt("pos");
        Fresco.initialize(this);
        detailsViewModel.getDetalis(numberId);
        detailsViewModel.getCast(numberId);
        setUP();

    }

    @SuppressLint("WrongConstant")
    private void setUP() {
        detailsViewModel.listMutableLiveDataDetails.observe(DetailsActivity.this, new Observer<DetailsMovieModel>() {
            @Override
            public void onChanged(DetailsMovieModel detailsMovieModel) {
                binding.tvNameMoviesDetail.setText(detailsMovieModel.getTitle());
                binding.tvTypeMoviesDetail.setText(detailsViewModel.getTypeGenre());
                binding.tvReatDetail.setText(detailsMovieModel.getVoteAverage().toString());
                binding.tvVoteAverageDetailCasting.setText("Review");
                binding.tvTextDetail.setText(detailsMovieModel.getOverview());
                binding.myImageViewDetail.setImageURI(Uri.parse("https://image.tmdb.org/t/p/w500"+detailsMovieModel.getPosterPath()));
            }
        });

        binding.imageViewBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsActivity.this, HomeActivity.class));
                finish();
            }
        });

        binding.recyclerViewDetailCast.setLayoutManager(new LinearLayoutManager(this,
                LinearLayout.HORIZONTAL,false));
        recycleStarCastAdapter=new RecycleStarCastAdapter(this);
       detailsViewModel.castlistMutableLiveData.observe(this, new Observer<ArrayList<Cast>>() {
           @Override
           public void onChanged(ArrayList<Cast> casts) {
               recycleStarCastAdapter.setList(casts);
               binding.recyclerViewDetailCast.setAdapter(recycleStarCastAdapter);
           }
       });


    }


}