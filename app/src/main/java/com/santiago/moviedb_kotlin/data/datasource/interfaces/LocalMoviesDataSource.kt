package com.santiago.moviedb_kotlin.data.datasource.interfaces

import com.santiago.moviedb_kotlin.data.datasource.room.entities.MovieEntity

interface LocalMoviesDataSource {
    suspend fun getPopularMovies(): ArrayList<MovieEntity>
    suspend fun getMovieDetail(id: Int): MovieEntity
    suspend fun saveMovies(movies: ArrayList<MovieEntity>)
}