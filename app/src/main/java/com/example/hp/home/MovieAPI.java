package com.example.hp.home;

/**
 * Created by hp on 19-04-2016.
 */

import com.example.hp.home.models.Example;
import retrofit.Callback;
import retrofit.http.GET;

public interface MovieAPI {
    @GET("/popular?api_key=0877e1cdb4c40aedf0b8c398906658b0")   //returns Example type json object
    void getPopular(Callback<Example> response);

    @GET("/now_playing?api_key=0877e1cdb4c40aedf0b8c398906658b0")   //returns Example type json object
    void getNowPlaying(Callback<Example> response);

    @GET("/upcoming?api_key=0877e1cdb4c40aedf0b8c398906658b0")   //returns Example type json object
    void getUpcoming(Callback<Example> response);


}
