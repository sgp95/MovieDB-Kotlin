package com.santiago.moviedb_kotlin.data.datasource.interfaces

import com.santiago.moviedb_kotlin.data.datasource.api.response.MovieResponse

interface RemoteMoviesDataSource {
    suspend fun getPopularMovies(): ArrayList<MovieResponse>
    suspend fun getMovieDetail(id: Int): MovieResponse
}