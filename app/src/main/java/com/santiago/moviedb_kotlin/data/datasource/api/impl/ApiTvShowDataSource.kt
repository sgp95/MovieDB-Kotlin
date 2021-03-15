package com.santiago.moviedb_kotlin.data.datasource.api.impl

import com.santiago.moviedb_kotlin.BuildConfig
import com.santiago.moviedb_kotlin.data.datasource.api.Services
import com.santiago.moviedb_kotlin.data.datasource.api.response.TvShowResponse
import com.santiago.moviedb_kotlin.data.datasource.interfaces.RemoteTvShowsDataSource
import javax.inject.Inject

class ApiTvShowDataSource @Inject constructor(private val services: Services): RemoteTvShowsDataSource {
    override suspend fun getPopularTvShows() =
        services.requestPopularTvShows(BuildConfig.API_KEY).results

    override suspend fun getTvShowDetail(id: Int) =
        services.requestTvShow(id, BuildConfig.API_KEY)
}