package com.example.movieapi.ui.di

import com.example.movieapi.data.api.ApiService
import com.example.movieapi.data.datasource.popularMovies.source.PopularMoviesDataSource
import com.example.movieapi.data.datasource.popularMovies.impl.PopularMoviesDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceDi {

    @Provides
    @Singleton
    fun providePopularMoviesRemoteDataSource(apiService: ApiService): PopularMoviesDataSource {
        return PopularMoviesDataSourceImpl(apiService)
    }

}