package com.example.hp.home;

/**
 * Created by hp on 19-04-2016.
 */

import com.example.hp.home.models.Example;
import com.example.hp.home.models.MovieModel;

import java.util.List;


import retrofit.Callback;
import retrofit.http.GET;

import retrofit.http.Path;

public interface MovieAPI {
    @GET("/popular?api_key=0877e1cdb4c40aedf0b8c398906658b0")   //returns Example type json object  //DO NOT START WITH '/'
    void getPopular(Callback<Example> response);


}
