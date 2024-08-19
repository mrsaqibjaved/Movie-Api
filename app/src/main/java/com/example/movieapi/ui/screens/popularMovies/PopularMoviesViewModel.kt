package com.example.movieapi.ui.screens.popularMovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapi.data.uiState.ApiResponse
import com.example.movieapi.data.uiState.PopularMoviesUiState
import com.example.movieapi.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    private val _popularMoviesUiState = MutableStateFlow(PopularMoviesUiState())
    val popularMoviesUiState = _popularMoviesUiState.asStateFlow()


    fun getPopularMoviesUiState(apiKey: String) {
        viewModelScope.launch {
            _popularMoviesUiState.update {
                val responseResult = getPopularMoviesUseCase.execute(apiKey)
                val apiResponse: ApiResponse =
                    if (responseResult.isNotEmpty()) ApiResponse.Success(responseResult)
                    else {
                        ApiResponse.Error
                    }
                it.copy(
                    apiResponse = apiResponse
                )
            }
        }
    }
}