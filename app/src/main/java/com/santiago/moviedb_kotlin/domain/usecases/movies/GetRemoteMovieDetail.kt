package com.santiago.moviedb_kotlin.domain.usecases.movies

import com.santiago.moviedb_kotlin.data.repository.MoviesRepository
import com.santiago.moviedb_kotlin.domain.models.DataMapper
import com.santiago.moviedb_kotlin.domain.models.Movie
import javax.inject.Inject

class GetRemoteMovieDetail @Inject constructor(private val repository: MoviesRepository) {
    suspend fun execute(movieId: Int): Movie = DataMapper.Movies.fromRemote(repository.getMovieDetail(movieId))
}