package com.example.movielisjtcompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movielisjtcompose.model.MovieData
import com.example.movielisjtcompose.ui.screens.MovieDetailScreen
import com.example.movielisjtcompose.ui.screens.MovieListScreen

@Composable
fun MovieApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            MovieListScreen(navController = navController)
        }
        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            val movie = MovieData.movies.find { it.id == movieId }
            movie?.let { MovieDetailScreen(it) }
        }
    }
}