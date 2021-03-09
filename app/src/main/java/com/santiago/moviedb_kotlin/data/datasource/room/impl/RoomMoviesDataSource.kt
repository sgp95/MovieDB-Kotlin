package com.santiago.moviedb_kotlin.data.datasource.room.impl

import com.santiago.moviedb_kotlin.data.datasource.interfaces.LocalMoviesDataSource
import com.santiago.moviedb_kotlin.data.datasource.room.AppDatabase
import com.santiago.moviedb_kotlin.data.datasource.room.entities.MovieEntity
import javax.inject.Inject

class RoomMoviesDataSource @Inject constructor(private val database: AppDatabase): LocalMoviesDataSource {

    override suspend fun getPopularMovies() = database.moviesDao().getAll()

    override suspend fun getMovieDetail(id: Int) = database.moviesDao().getMovie(id)

    override suspend fun saveMovies(movies: ArrayList<MovieEntity>) = database.moviesDao().insertAll(movies)
}