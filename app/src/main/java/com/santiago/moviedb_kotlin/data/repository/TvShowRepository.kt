package com.santiago.moviedb_kotlin.data.repository

import com.santiago.moviedb_kotlin.data.datasource.interfaces.LocalTvShowsDataSource
import com.santiago.moviedb_kotlin.data.datasource.interfaces.RemoteTvShowsDataSource
import com.santiago.moviedb_kotlin.data.datasource.room.entities.TvShowEntity
import javax.inject.Inject

class TvShowRepository @Inject constructor(private val remoteDataSource: RemoteTvShowsDataSource, private val localDataSource: LocalTvShowsDataSource) {
    suspend fun getPopularTvShow() = remoteDataSource.getPopularTvShows()

    suspend fun getTvShow(tvShowId: Int) = remoteDataSource.getTvShowDetail(tvShowId)

    suspend fun getLocalPopularTvShows() = localDataSource.getPopularTvShows()

    suspend fun getLocalTvShow(tvShowId: Int) = localDataSource.getTvShowDetail(tvShowId)

    suspend fun saveLocalTvShows(tvShows: ArrayList<TvShowEntity>) = localDataSource.saveTvShows(tvShows)
}