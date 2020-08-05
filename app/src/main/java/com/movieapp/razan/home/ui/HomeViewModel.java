package com.movieapp.razan.home.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.movieapp.razan.home.data.ApiClient;
import com.movieapp.razan.home.data.ApiInterface;
import com.movieapp.razan.home.model.Result;
import com.movieapp.razan.home.model.TrendingModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private final static String API_KEY = "f0dd213b514dd22fa6d7790fdae32949";
    MutableLiveData<ArrayList<Result>> listMutableLiveDataTrending =
            new MutableLiveData<>();

    public void getTrending() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<TrendingModel> call = apiService.getTrendingMovies(API_KEY);

        call.enqueue(new Callback<TrendingModel>() {
            @Override
            public void onResponse(Call<TrendingModel> call, Response<TrendingModel> response) {
                Log.d("TAGR", "Number of movies received: " + response.body().getResults().get(0).getPosterPath());
                TrendingModel trendingModel = response.body();
                ArrayList<Result> resultModels =  response.body().getResults();
                listMutableLiveDataTrending.setValue(resultModels);
            }

            @Override
            public void onFailure(Call<TrendingModel> call, Throwable t) {
                Log.e("TAGR", t.toString());
            }
        });
        ////////////////////////
     /*   ApiClient.getINSTANNCE().getTrendingMovies(API_KEY).enqueue(new Callback<TrendingModel>() {
            @Override
            public void onResponse(Call<TrendingModel> call, Response<TrendingModel> response) {
                TrendingModel trendingModel = response.body();
                listMutableLiveDataTrending
                        .setValue((ArrayList<ResultModel>) trendingModel.getResultModels());
            }

            @Override
            public void onFailure(Call<TrendingModel> call, Throwable t) {
                Log.d("Failure", t.getMessage());

            }
        });*/

    }
}