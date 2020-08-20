package com.movieapp.razan.home.ui.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.movieapp.razan.home.data.ApiClient;
import com.movieapp.razan.home.data.ApiInterface;
import com.movieapp.razan.home.model.DetailsMovieModel;
import com.movieapp.razan.home.model.Genre;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsViewModel extends ViewModel {

    private final static String API_KEY = "f0dd213b514dd22fa6d7790fdae32949";
    public MutableLiveData<DetailsMovieModel> listMutableLiveDataDetails =
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


    public String getTypeGenre(ArrayList<Genre> g) {
        for (int i = 0; i < g.size(); i++) {
            for (int j = 0; j < HomeViewModel.genresHome.size(); j++) {
                int q = g.get(i).getId();
                if (HomeViewModel.genresHome.get(j).getId() == q)
                    type += HomeViewModel.genresHome.get(j).getName() + "/";
            }

        }
        return type;
    }
}
