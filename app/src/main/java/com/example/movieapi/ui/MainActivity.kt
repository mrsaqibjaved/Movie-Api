package com.example.movieapi.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieapi.data.DataSource
import com.example.movieapi.ui.screens.popularMovies.PopularMoviesScreen
import com.example.movieapi.ui.screens.popularMovies.PopularMoviesViewModel
import com.example.movieapi.ui.theme.MovieApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieApiTheme {

                val popularMoviesViewModel: PopularMoviesViewModel = hiltViewModel()
                val popularMoviesUiState by popularMoviesViewModel.popularMoviesUiState.collectAsStateWithLifecycle()

                popularMoviesViewModel.getPopularMoviesUiState(DataSource.API_KEY)

                PopularMoviesScreen(apiResponse = popularMoviesUiState.apiResponse){id->
                    Toast.makeText(applicationContext,"$id",Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}
