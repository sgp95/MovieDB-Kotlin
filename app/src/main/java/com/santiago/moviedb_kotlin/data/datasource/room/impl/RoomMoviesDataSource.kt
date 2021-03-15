package com.santiago.moviedb_kotlin.data.datasource.room.impl

import com.santiago.moviedb_kotlin.data.datasource.interfaces.LocalMoviesDataSource
import com.santiago.moviedb_kotlin.data.datasource.room.AppDatabase
import com.santiago.moviedb_kotlin.data.datasource.room.dao.MoviesDao
import com.santiago.moviedb_kotlin.data.datasource.room.entities.MovieEntity
import javax.inject.Inject

class RoomMoviesDataSource @Inject constructor(private val moviesDao: MoviesDao): LocalMoviesDataSource {

    override suspend fun getPopularMovies(): ArrayList<MovieEntity> = ArrayList(moviesDao.getAll())

    override suspend fun getMovieDetail(id: Int) = moviesDao.getMovie(id)

    override suspend fun saveMovies(movies: ArrayList<MovieEntity>) {
        moviesDao.deleteAll()
        moviesDao.insertAll(movies)
    }
}