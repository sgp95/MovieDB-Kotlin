package com.santiago.moviedb_kotlin.data.datasource.api

import com.santiago.moviedb_kotlin.data.datasource.api.response.BaseResponse
import com.santiago.moviedb_kotlin.data.datasource.api.response.MovieResponse
import com.santiago.moviedb_kotlin.data.datasource.api.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {

    @GET(Endpoints.POPULAR_MOVIES)
    suspend fun requestPopularMovies(@Query("api_key") apiKey: String): BaseResponse<MovieResponse>

    @GET(Endpoints.MOVIE_DETAIL)
    suspend fun requestMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieResponse

    @GET(Endpoints.POPULAR_TV_SHOW)
    suspend fun requestPopularTvShows(@Query("api_key") apiKey: String): BaseResponse<TvShowResponse>

    @GET(Endpoints.TV_SHOW_DETAIL)
    suspend fun requestTvShow(
        @Path("tv_id") tvShowId: Int,
        @Query("api_key") apiKey: String
    ): TvShowResponse
}