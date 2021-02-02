package com.santiago.moviedb_kotlin.data.datasource.api.impl

import com.santiago.moviedb_kotlin.BuildConfig
import com.santiago.moviedb_kotlin.data.datasource.api.Services
import com.santiago.moviedb_kotlin.data.datasource.interfaces.RemoteMoviesDataSource
import javax.inject.Inject

class ApiMoviesDataSource @Inject constructor(private val services: Services): RemoteMoviesDataSource {
    override suspend fun getPopularMovies() =
        services.requestPopularMovies(BuildConfig.API_KEY).results

    override suspend fun getMovieDetail(id: Int) =
        services.requestMovie(BuildConfig.API_KEY, id)

}