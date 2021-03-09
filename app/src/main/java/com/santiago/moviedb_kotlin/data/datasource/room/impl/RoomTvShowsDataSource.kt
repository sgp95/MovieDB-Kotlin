package com.santiago.moviedb_kotlin.data.datasource.room.impl

import com.santiago.moviedb_kotlin.data.datasource.interfaces.LocalTvShowsDataSource
import com.santiago.moviedb_kotlin.data.datasource.room.AppDatabase
import com.santiago.moviedb_kotlin.data.datasource.room.entities.TvShowEntity
import javax.inject.Inject

class RoomTvShowsDataSource @Inject constructor(private val database: AppDatabase): LocalTvShowsDataSource {
    override suspend fun getPopularTvShows() = database.tvShowDao().getAll()

    override suspend fun getTvShowDetail(id: Int) = database.tvShowDao().getTvShow(id)

    override suspend fun saveTvShows(tvShows: ArrayList<TvShowEntity>) = database.tvShowDao().insertAll(tvShows)
}