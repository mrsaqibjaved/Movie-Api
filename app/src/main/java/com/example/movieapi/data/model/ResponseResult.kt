package com.example.movieapi.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseResult(
    val results: List<Result>,
)
