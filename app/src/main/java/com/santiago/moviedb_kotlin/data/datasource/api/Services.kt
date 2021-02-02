package com.santiago.moviedb_kotlin.data.datasource.api

import com.santiago.moviedb_kotlin.data.datasource.api.response.BaseResponse
import com.santiago.moviedb_kotlin.data.datasource.api.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {

    @GET(Endpoints.POPULAR_MOVIES)
    suspend fun requestPopularMovies(@Query("api_key") apiKey: String): BaseResponse<MovieResponse>

    @GET(Endpoints.MOVIE_DETAIL)
    suspend fun requestMovie(
        @Query("api_key") apiKey: String,
        @Path("movie_id") movieId: Int
    ): MovieResponse
}