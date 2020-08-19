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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageViewModel extends ViewModel {
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Meal>> mealLiveData=new MutableLiveData<ArrayList<Meal>>();
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


    public void getMealList() {
        mealLiveData.setValue( prepareArray());
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
