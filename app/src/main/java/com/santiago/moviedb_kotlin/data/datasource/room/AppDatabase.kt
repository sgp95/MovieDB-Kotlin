package com.santiago.moviedb_kotlin.data.datasource.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.santiago.moviedb_kotlin.data.datasource.room.dao.MoviesDao
import com.santiago.moviedb_kotlin.data.datasource.room.dao.TvShowDao
import com.santiago.moviedb_kotlin.data.datasource.room.entities.MovieEntity
import com.santiago.moviedb_kotlin.data.datasource.room.entities.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun tvShowDao(): TvShowDao
}