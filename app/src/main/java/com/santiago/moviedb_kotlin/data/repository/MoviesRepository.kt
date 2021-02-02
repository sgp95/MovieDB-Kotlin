package com.santiago.moviedb_kotlin.data.repository

import com.santiago.moviedb_kotlin.data.datasource.interfaces.RemoteMoviesDataSource
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val remoteDataSource: RemoteMoviesDataSource) {
    suspend fun getPopularMovies() = remoteDataSource.getPopularMovies()

    suspend fun getMovieDetail(movieId: Int) = remoteDataSource.getMovieDetail(movieId)
}