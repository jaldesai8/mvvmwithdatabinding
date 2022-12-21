package com.demo.databindingmvvm.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.databindingmvvm.adapter.MovieAdapter
import com.demo.databindingmvvm.databinding.ActivityImageBinding
import com.demo.databindingmvvm.model.MovieModel
import com.demo.databindingmvvm.pagination.PaginationScrollListener
import com.demo.databindingmvvm.viewmodel.MovieViewModel

class ImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    private var pageCount = 1
    private var totalPage = 1
    private var isLastPage = false
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieAdapter = MovieAdapter()
        binding.rvMovies.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(applicationContext, 2)
        }

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.getMovies(pageCount)

        binding.rvMovies.addOnScrollListener(object :
            PaginationScrollListener(binding.rvMovies.layoutManager as GridLayoutManager) {
            override fun loadMoreItems() {
                pageCount += 1
                viewModel.getMovies(pageCount)
            }
            override val totalPageCount: Int get() = totalPage
            override val isLastPage: Boolean get() = this@ImageActivity.isLastPage
            override val isLoading: Boolean get() = this@ImageActivity.isLoading
        })

        viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
            movieAdapter.setMovieList(movieList)
        })

        viewModel.totalPage().observe(this, Observer { totalPage ->
            this.totalPage = totalPage
            this.totalPage =+1
        })

    }

}
