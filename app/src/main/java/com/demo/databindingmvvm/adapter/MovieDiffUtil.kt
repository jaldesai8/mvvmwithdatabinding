package com.demo.databindingmvvm.adapter

import androidx.recyclerview.widget.DiffUtil

class MovieDiffUtil(private val oldMovieList : ArrayList<com.demo.databindingmvvm.model.Result>,
                   private val newMovieList : ArrayList<com.demo.databindingmvvm.model.Result>) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldMovieList.size
    }

    override fun getNewListSize(): Int {
        return newMovieList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovieList[oldItemPosition].title == newMovieList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
         val oldTitle = oldMovieList[oldItemPosition]
         val newTitle = newMovieList[newItemPosition]
        return oldTitle.title == newTitle.title
    }

}