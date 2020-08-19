package com.movieapp.razan.home.data;

import com.movieapp.razan.home.model.GenersListModel;
import com.movieapp.razan.home.model.MoviesByGenerIdModel;
import com.movieapp.razan.home.model.TrendingModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<TrendingModel> getTrendingMovies(@Query("api_key") String apiKey);

@GET("genre/movie/list")
    Call<GenersListModel>getGenreList(@Query("api_key") String apiKey);

    @GET("discover/movie/{page}/{with_genres}")
    Call<MoviesByGenerIdModel>gerMoviesByGenerIdModel(@Path("page") int page,@Path("with_genres") int genres, @Query("api_key") String apiKey);
}
