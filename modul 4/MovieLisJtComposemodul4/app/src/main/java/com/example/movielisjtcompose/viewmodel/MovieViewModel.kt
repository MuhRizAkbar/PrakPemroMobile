package com.example.movielisjtcompose.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movielisjtcompose.model.Movie
import com.example.movielisjtcompose.model.MovieData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class MovieViewModel(val categoryType: String) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    // StateFlow for handling the navigation event
    private val _navigateToDetail = MutableStateFlow<Movie?>(null)
    val navigateToDetail: StateFlow<Movie?> = _navigateToDetail.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        _movies.value = MovieData.movies
        // Logging event A: Log saat data item masuk ke dalam list
        Timber.d("Data loaded into StateFlow. Total items: ${_movies.value.size}")
    }

    fun onMovieClicked(movieId: Int) {
        val selectedMovie = _movies.value.find { it.id == movieId }
        _navigateToDetail.value = selectedMovie
    }

    fun onNavigationDone() {
        _navigateToDetail.value = null
    }
}