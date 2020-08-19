package com.movieapp.razan.home.ui;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.movieapp.razan.home.data.ApiClient;
import com.movieapp.razan.home.data.ApiInterface;
import com.movieapp.razan.home.model.GenersListModel;
import com.movieapp.razan.home.model.Genre;
import com.movieapp.razan.home.model.Meal;
import com.movieapp.razan.home.model.MoviesByGenerIdModel;
import com.movieapp.razan.home.model.Result;
import com.movieapp.razan.home.model.ResultPager;
import com.movieapp.razan.home.model.TrendingModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageViewModel extends ViewModel {
    private final static String API_KEY = "f0dd213b514dd22fa6d7790fdae32949";

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Meal>> mealLiveData = new MutableLiveData<ArrayList<Meal>>();
    public MutableLiveData<ArrayList<ResultPager>> resaltMutableLiveData = new MutableLiveData<ArrayList<ResultPager>>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void getMoviesByGenerId(int page, int genres) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesByGenerIdModel> call = apiService.gerMoviesByGenerIdModel(API_KEY,page, genres);

        call.enqueue(new Callback<MoviesByGenerIdModel>() {
            @Override
            public void onResponse(Call<MoviesByGenerIdModel> call, Response<MoviesByGenerIdModel> response) {
                Log.d("TAGR", "Number of movies received: " + response.body());
                response.body().getResults();

                ArrayList<ResultPager> movies = response.body().getResults();
                resaltMutableLiveData.setValue(movies);
            }

            @Override
            public void onFailure(Call<MoviesByGenerIdModel> call, Throwable t) {
                Log.e("TAGR", t.toString());
            }
        });

    }


    public void getMealList() {
        mealLiveData.setValue(prepareArray());
    }

    private ArrayList<Meal> prepareArray() {
        ArrayList<Meal> m = new ArrayList<>();

        Meal p1 = new Meal();
        p1.setMealName("Burger");
        p1.setMealPrice("3");
        m.add(p1);

        p1 = new Meal();
        p1.setMealName("Pizza");
        p1.setMealPrice("4");
        m.add(p1);

        return m;
    }


}
