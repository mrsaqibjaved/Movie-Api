package com.example.movieapi.data.api

import com.example.movieapi.data.model.ResponseResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")apiKey:String
    ): ResponseResult

}