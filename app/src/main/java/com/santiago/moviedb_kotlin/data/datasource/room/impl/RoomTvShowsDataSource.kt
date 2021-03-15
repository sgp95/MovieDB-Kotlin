package com.santiago.moviedb_kotlin.data.datasource.room.impl

import com.santiago.moviedb_kotlin.data.datasource.interfaces.LocalTvShowsDataSource
import com.santiago.moviedb_kotlin.data.datasource.room.AppDatabase
import com.santiago.moviedb_kotlin.data.datasource.room.dao.TvShowDao
import com.santiago.moviedb_kotlin.data.datasource.room.entities.TvShowEntity
import javax.inject.Inject

class RoomTvShowsDataSource @Inject constructor(private val tvShowsDao: TvShowDao): LocalTvShowsDataSource {
    override suspend fun getPopularTvShows():ArrayList<TvShowEntity> = ArrayList(tvShowsDao.getAll())

    override suspend fun getTvShowDetail(id: Int) = tvShowsDao.getTvShow(id)

    override suspend fun saveTvShows(tvShows: ArrayList<TvShowEntity>) {
        tvShowsDao.deleteAll()
        tvShowsDao.insertAll(tvShows)
    }
}