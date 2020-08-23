package com.movieapp.razan.home.ui.viewmodels;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImageTranscoderType;
import com.facebook.imagepipeline.core.MemoryChunkType;
import com.movieapp.razan.home.data.ApiClient;
import com.movieapp.razan.home.data.ApiInterface;
import com.movieapp.razan.home.model.Cast;
import com.movieapp.razan.home.model.DetailsMovieModel;
import com.movieapp.razan.home.model.Genre;
import com.movieapp.razan.home.model.MovieCastModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsViewModel extends ViewModel {

    private final static String API_KEY = "f0dd213b514dd22fa6d7790fdae32949";
    public MutableLiveData<DetailsMovieModel> listMutableLiveDataDetails =
            new MutableLiveData<>();

    public MutableLiveData<ArrayList<Cast>> castlistMutableLiveData =
            new MutableLiveData<>();
    String type = ""; ArrayList<Genre>genres;


    public void getDetalis(int id) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<DetailsMovieModel> call = apiService.getDetailsMovies(id, API_KEY);
        call.enqueue(new Callback<DetailsMovieModel>() {
            @Override
            public void onResponse(Call<DetailsMovieModel> call, Response<DetailsMovieModel> response) {

                Log.d("TAGR", "" + response.body());
                DetailsMovieModel detailsMovieModels = response.body();
               genres=response.body().getGenres();
                listMutableLiveDataDetails.setValue(detailsMovieModels);
            }

            @Override
            public void onFailure(Call<DetailsMovieModel> call, Throwable t) {
                Log.d(" MovieInformationActivityError", t.toString());
            }
        });

    }


    public String getTypeGenre() {
        for (int i = 0; i < genres.size(); i++) {
            for (int j = 0; j < HomeViewModel.genresHome.size(); j++) {
                int q = genres.get(i).getId();
                if (HomeViewModel.genresHome.get(j).getId() == q)
                   if(i==genres.size()-1)
                       type += HomeViewModel.genresHome.get(j).getName() + ".";
                else
                    type += HomeViewModel.genresHome.get(j).getName() + "/";
            }

        }

        return type;
    }


    public void getCast(int id){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MovieCastModel> call=apiService.getStarCast(id,"credits",API_KEY);
        call.enqueue(new Callback<MovieCastModel>() {
            @Override
            public void onResponse(Call<MovieCastModel> call, Response<MovieCastModel> response) {
                Log.d("TAGR", "" + response.body());
              ArrayList<Cast> cast= response.body().getCast();
                castlistMutableLiveData.setValue(cast);
            }

            @Override
            public void onFailure(Call<MovieCastModel> call, Throwable t) {
                Log.d(" Movie Cast Error", t.toString());
            }
        });
    }

}
