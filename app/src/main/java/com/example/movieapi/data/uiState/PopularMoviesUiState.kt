package com.example.movieapi.data.uiState

import com.example.movieapi.data.model.Result

data class PopularMoviesUiState(
    val apiResponse: ApiResponse = ApiResponse.Loading
)
sealed interface ApiResponse{
    data class Success(val responseResult: List<Result>): ApiResponse
    data object Loading: ApiResponse
    data object Error: ApiResponse
}
