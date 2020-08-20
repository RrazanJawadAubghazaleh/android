package com.movieapp.razan.home.data;

import com.movieapp.razan.home.model.DetailsMovieModel;
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

    @GET("discover/movie")
    Call<MoviesByGenerIdModel>gerMoviesByGenerIdModel( @Query("api_key") String apiKey,
                                                       @Query("page") int page,
                                                      @Query("with_genres") int genres);


    @GET("movie/{movie_id}")
    Call<DetailsMovieModel>getDetailsMovies(@Path("movie_id") int id, @Query("api_key") String apiKey);

  /*  @GET("movie/{movie_id}/{credits}")
    Call<DetailsMovieModel>getStarCast(@Path("movie_id") int id,
                                       @Path("credits") String credits,
                                       @Query("api_key") String apiKey);*/
}
