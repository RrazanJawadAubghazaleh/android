package com.movieapp.razan.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GenersListModel {

    @SerializedName("genres")
    private  ArrayList<Genre> genres = null;

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres( ArrayList<Genre> genres) {
        this.genres = genres;
    }
}
