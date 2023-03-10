package com.demo.databindingmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.databindingmvvm.R
import com.demo.databindingmvvm.databinding.ItemMovieBinding
import com.demo.databindingmvvm.model.Result


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movieList = ArrayList<Result>()
//    fun setMovieList(movieList: List<Result>) {
//        this.movieList = movieList as ArrayList<Result>
//        notifyItemChanged(movieList.size)
//    }

    fun setMovieList(newMovieList: List<Result>) {
        val diffCallback = MovieDiffUtil(movieList,newMovieList as ArrayList<Result>)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.movieList.clear()
        this.movieList.addAll(newMovieList)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Glide.with(holder.itemView)
//            .load("https://image.tmdb.org/t/p/w500" + movieList[position].poster_path)
//            .into(holder.binding.movieImage)
//        holder.binding.movieName.text = movieList[position].title
        holder.binding.movies = movieList[position]
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}