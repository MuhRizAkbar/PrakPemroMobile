package com.example.movielisjtcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movielisjtcompose.ui.components.MovieListItem
import com.example.movielisjtcompose.viewmodel.MovieViewModel
import com.example.movielisjtcompose.viewmodel.MovieViewModelFactory
import timber.log.Timber

@Composable
fun MovieListScreen(
    navController: NavController,
    // Initialize ViewModel using the Factory we made in Part B
    viewModel: MovieViewModel = viewModel(factory = MovieViewModelFactory("Horror/Thriller"))
) {
    // Collect StateFlows safely using lifecycle awareness
    val movieList by viewModel.movies.collectAsStateWithLifecycle()
    val navigateToDetail by viewModel.navigateToDetail.collectAsStateWithLifecycle()

    // Handle Navigation Event
    LaunchedEffect(navigateToDetail) {
        navigateToDetail?.let { movie ->
            // Logging event C: Log data ketika berpindah ke halaman Detail
            Timber.i("Navigating to Detail Screen. Selected Movie Data: ID=${movie.id}, Title=${movie.title}, Year=${movie.year}")

            navController.navigate("detail/${movie.id}")

            // Reset the state so it doesn't re-trigger on recomposition
            viewModel.onNavigationDone()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(movieList) { movie ->
            MovieListItem(
                movie = movie,
                onDetailClick = {
                    // Send the click event to the ViewModel instead of navigating directly
                    viewModel.onMovieClicked(movie.id)
                }
            )
        }
    }
}