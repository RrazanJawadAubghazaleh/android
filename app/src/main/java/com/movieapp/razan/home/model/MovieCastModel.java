package com.movieapp.razan.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieCastModel {
    @SerializedName("id")
    private Integer id;
    @SerializedName("cast")

    private ArrayList<Cast> cast = null;
    @SerializedName("crew")
    private ArrayList<Crew> crew = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Cast> cast) {
        this.cast = cast;
    }

    public ArrayList<Crew> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<Crew> crew) {
        this.crew = crew;
    }
}
