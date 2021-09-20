package com.aziz.azdbmovie.api;

import com.aziz.azdbmovie.model.MovieModel;

import retrofit2.http.GET;
import retrofit2.Call;


public interface ApiService{
    @GET("movie/upcoming?api_key=af7b99960799255e9146964257358445")
    Call<MovieModel> upComing();

    @GET("movie/now_playing?api_key=af7b99960799255e9146964257358445")
    Call<MovieModel> nowPlaying();

    @GET("movie/popular?api_key=af7b99960799255e9146964257358445")
    Call<MovieModel> PopularMovie();

}
