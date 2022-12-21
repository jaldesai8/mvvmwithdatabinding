package com.demo.databindingmvvm.viewmodel

import android.util.Log
import android.widget.ImageView
import android.widget.Toast
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

class MovieViewModel : ViewModel() {

    private var isFirstTimeLoaded: Boolean = false
    private var totalPage = MutableLiveData<Int>()

    private var movieLiveData = MutableLiveData<List<Result>>()
    private var list = ArrayList<Result>()
    fun getMovies(page: Int) {
        isFirstTimeLoaded = true
        RetrofitBuilder.api.getMovies("69d66957eebff9666ea46bd464773cf0", page).enqueue(object :
            Callback<MovieModel> {
            override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                if (response.body() != null) {
                    totalPage.value = response.body()?.total_pages ?: 1
//                    if(page==1){
//                        list.clear()
//                        list.addAll(response.body()!!.results)
//                        movieLiveData.value = list as List<Result>
                //    } else {
                        list.addAll(response.body()!!.results)
                        movieLiveData.value = list
                 //   }
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }
    fun observeMovieLiveData() : LiveData<List<Result>> {
        return movieLiveData
    }

    fun totalPage(): LiveData<Int> {
        return totalPage
    }

    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun loadImages(view: ImageView, url: String?) {
            //Log.d("hello",url)
            if(url != null){
                Glide.with(view)
                    .load("https://image.tmdb.org/t/p/w500$url")
                    .into(view)
            } else {
               return
            }
        }
    }
}