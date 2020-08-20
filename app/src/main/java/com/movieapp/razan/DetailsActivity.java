package com.movieapp.razan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.movieapp.razan.databinding.ActivityDetailsBinding;
import com.movieapp.razan.home.model.DetailsMovieModel;
import com.movieapp.razan.home.ui.viewmodels.DetailsViewModel;
import com.movieapp.razan.home.ui.viewmodels.HomeViewModel;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    DetailsViewModel detailsViewModel;
    int numberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_details);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        numberId = getIntent().getExtras().getInt("pos");
        detailsViewModel.getDetalis(numberId);
        setUP();

    }

    private void setUP() {
        detailsViewModel.listMutableLiveDataDetails.observe(DetailsActivity.this, new Observer<DetailsMovieModel>() {
            @Override
            public void onChanged(DetailsMovieModel detailsMovieModel) {
                binding.tvNameMoviesDetail.setText(detailsMovieModel.getTitle());
                binding.tvTypeMoviesDetail.setText(detailsMovieModel.getGenres().toString());
                binding.tvReatDetail.setText(detailsMovieModel.getVoteAverage().toString());
                binding.tvVoteAverageDetail.setText(detailsMovieModel.getPopularity().toString()+R.string.review);
                binding.tvTextDetail.setText(detailsMovieModel.getOverview());
            }
        });
    }


}