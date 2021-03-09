package com.santiago.moviedb_kotlin.data.repository

import com.santiago.moviedb_kotlin.data.datasource.interfaces.LocalMoviesDataSource
import com.santiago.moviedb_kotlin.data.datasource.interfaces.RemoteMoviesDataSource
import com.santiago.moviedb_kotlin.data.datasource.room.entities.MovieEntity
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val remoteDataSource: RemoteMoviesDataSource, private val localDataSource: LocalMoviesDataSource) {
    suspend fun getPopularMovies() = remoteDataSource.getPopularMovies()

    suspend fun getMovieDetail(movieId: Int) = remoteDataSource.getMovieDetail(movieId)

    suspend fun getLocalPopularMovies() = localDataSource.getPopularMovies()

    suspend fun getLocalMovieDetail(movieId: Int) = localDataSource.getMovieDetail(movieId)

    suspend fun saveLocalMovies(movies: ArrayList<MovieEntity>) = localDataSource.saveMovies(movies)
}