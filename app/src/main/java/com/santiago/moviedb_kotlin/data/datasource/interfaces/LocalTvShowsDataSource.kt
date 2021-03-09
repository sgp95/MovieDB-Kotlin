package com.santiago.moviedb_kotlin.data.datasource.interfaces

import com.santiago.moviedb_kotlin.data.datasource.room.entities.TvShowEntity

interface LocalTvShowsDataSource {
    suspend fun getPopularTvShows(): ArrayList<TvShowEntity>
    suspend fun getTvShowDetail(id: Int): TvShowEntity
    suspend fun saveTvShows(tvShows: ArrayList<TvShowEntity>)
}