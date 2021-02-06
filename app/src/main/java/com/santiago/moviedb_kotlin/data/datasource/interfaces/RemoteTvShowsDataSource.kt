package com.santiago.moviedb_kotlin.data.datasource.interfaces

import com.santiago.moviedb_kotlin.data.datasource.api.response.TvShowResponse

interface RemoteTvShowsDataSource {
    suspend fun getPopularTvShows(): ArrayList<TvShowResponse>
    suspend fun getTvShowDetail(id: Int): TvShowResponse
}