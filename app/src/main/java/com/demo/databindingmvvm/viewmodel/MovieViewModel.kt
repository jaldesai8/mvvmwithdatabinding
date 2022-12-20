package com.demo.databindingmvvm.viewmodel

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.demo.databindingmvvm.model.MovieModel
import com.demo.databindingmvvm.model.Result
import com.demo.databindingmvvm.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL

class MovieViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<List<Result>>()
    fun getMovies() {
        RetrofitBuilder.api.getMovies("69d66957eebff9666ea46bd464773cf0").enqueue(object  :
            Callback<MovieModel> {
            override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                if (response.body()!=null) {
                    movieLiveData.value = response.body()!!.results
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observeMovieLiveData() : LiveData<List<Result>> {
        return movieLiveData
    }
    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun loadImages(view : ImageView, url: String){
            Log.d("hello",url)
            Glide.with(view)
            .load("https://image.tmdb.org/t/p/w500$url")
            .into(view)
        }
    }
}