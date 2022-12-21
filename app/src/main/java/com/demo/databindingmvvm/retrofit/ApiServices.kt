package com.demo.databindingmvvm.retrofit

import com.demo.databindingmvvm.model.MovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("popular?")
    fun getMovies(@Query("api_key") api_key : String, @Query("page") page : Int) : Call<MovieModel>
}