package com.movieapp.razan.home.data;

import com.movieapp.razan.home.model.TrendingModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<TrendingModel> getTrendingMovies(@Query("api_key") String apiKey);


}
