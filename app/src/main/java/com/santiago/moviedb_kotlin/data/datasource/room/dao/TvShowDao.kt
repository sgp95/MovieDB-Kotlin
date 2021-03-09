package com.santiago.moviedb_kotlin.data.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.santiago.moviedb_kotlin.data.datasource.room.entities.TvShowEntity

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tvshowentity")
    suspend fun getAll(): ArrayList<TvShowEntity>

    @Query("SELECT * FROM tvshowentity WHERE id = :tvShowId")
    suspend fun getTvShow(tvShowId: Int): TvShowEntity

    @Insert
    suspend fun insertAll(movies: ArrayList<TvShowEntity>)
}