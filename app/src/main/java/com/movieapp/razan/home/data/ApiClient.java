package com.movieapp.razan.home.data;

import com.movieapp.razan.home.model.TrendingModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private ApiInterface apiInterface;
    private static ApiClient INSTANNCE;
    private static Retrofit retrofit = null;


    public ApiClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface =retrofit.create(ApiInterface.class);

    }

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiClient getINSTANNCE() {
        if(INSTANNCE==null){
            INSTANNCE = new ApiClient();
        }
        return INSTANNCE;
    }


    public Call<TrendingModel> getTrendingMovies(String api_key) {
        return apiInterface.getTrendingMovies(api_key);
    }



}
