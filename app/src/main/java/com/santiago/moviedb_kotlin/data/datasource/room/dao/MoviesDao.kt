package com.santiago.moviedb_kotlin.data.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.santiago.moviedb_kotlin.data.datasource.room.entities.MovieEntity

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movieentity")
    suspend fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM movieentity WHERE id = :movieId")
    suspend fun getMovie(movieId: Int): MovieEntity

    @Insert
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("DELETE FROM movieentity")
    suspend fun deleteAll()
}