package com.santiago.moviedb_kotlin.data.repository

import com.santiago.moviedb_kotlin.data.datasource.interfaces.RemoteTvShowsDataSource
import javax.inject.Inject

class TvShowRepository @Inject constructor(private val remoteDataSource: RemoteTvShowsDataSource) {
    suspend fun getPopularTvShow() = remoteDataSource.getPopularTvShows()

    suspend fun getTvShow(tvShowId: Int) = remoteDataSource.getTvShowDetail(tvShowId)
}