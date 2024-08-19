package com.example.movieapi.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapi.R
import com.example.movieapi.data.DataSource
import com.example.movieapi.ui.screens.movieDetails.MovieDetailsScreen
import com.example.movieapi.ui.screens.movieDetails.MovieDetailsViewModel
import com.example.movieapi.ui.screens.popularMovies.PopularMoviesScreen
import com.example.movieapi.ui.screens.popularMovies.PopularMoviesViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController()

    val moviesViewModel: PopularMoviesViewModel = hiltViewModel()
    val moviesUiState by moviesViewModel.popularMoviesUiState.collectAsStateWithLifecycle()

    val movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
    val movieDetailsUiState by movieDetailsViewModel.movieDetailsUiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController, startDestination = MovieScreens.PopularMovies.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = MovieScreens.PopularMovies.route) {

            PopularMoviesScreen(apiResponse = moviesUiState.apiResponse) { id ->
                navController.navigate(MovieScreens.MovieDetail.withArgs(id))
            }
        }

        composable(route = MovieScreens.MovieDetail.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val movieId = entry.arguments?.getString("id") ?: "533535"
            movieDetailsViewModel.getMovieDetailsById(movieId.toInt(), DataSource.API_KEY)
            MovieDetailsScreen(movieDetailsResponse = movieDetailsUiState.movieDetailsResponse)
        }
    }
}


sealed class MovieScreens(val route: String, val titleResource: Int) {
    data object PopularMovies : MovieScreens("Popular Movies", R.string.popular_movies)
    data object MovieDetail : MovieScreens("Movie Details", R.string.movie_detail)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
