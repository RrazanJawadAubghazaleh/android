package com.movieapp.razan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.movieapp.razan.databinding.ActivityDetailsBinding;
import com.movieapp.razan.home.model.DetailsMovieModel;
import com.movieapp.razan.home.ui.HomeActivity;
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
        Fresco.initialize(this);
        detailsViewModel.getDetalis(numberId);
        setUP();

    }

    private void setUP() {
        detailsViewModel.listMutableLiveDataDetails.observe(DetailsActivity.this, new Observer<DetailsMovieModel>() {
            @Override
            public void onChanged(DetailsMovieModel detailsMovieModel) {
                binding.tvNameMoviesDetail.setText(detailsMovieModel.getTitle());
                binding.tvTypeMoviesDetail.setText(detailsViewModel.getTypeGenre());
                binding.tvReatDetail.setText(detailsMovieModel.getVoteAverage().toString());
                binding.tvVoteAverageDetail.setText(detailsMovieModel.getPopularity().toString()+R.string.review);
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
    }


}