package com.example.movieapi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapi.data.DataSource
import com.example.movieapi.ui.MovieScreens
import com.example.movieapi.ui.screens.movieDetails.MovieDetailsScreen
import com.example.movieapi.ui.screens.movieDetails.MovieDetailsViewModel
import com.example.movieapi.ui.screens.popularMovies.PopularMoviesScreen
import com.example.movieapi.ui.screens.popularMovies.PopularMoviesViewModel


@Composable
fun Navigation(modifier: Modifier,navController: NavHostController) {

    val moviesViewModel: PopularMoviesViewModel = hiltViewModel()
    val moviesUiState by moviesViewModel.popularMoviesUiState.collectAsStateWithLifecycle()

    val movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
    val movieDetailsUiState by movieDetailsViewModel.movieDetailsUiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController, startDestination = MovieScreens.PopularMovies.route,
        modifier = modifier
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


