package com.santiago.moviedb_kotlin.domain.usecases.movies

import com.santiago.moviedb_kotlin.data.repository.MoviesRepository
import com.santiago.moviedb_kotlin.domain.models.DataMapper
import com.santiago.moviedb_kotlin.domain.models.Movie
import javax.inject.Inject

class SaveLocalMovies @Inject constructor(private val repository: MoviesRepository) {
    suspend fun execute(movies: ArrayList<Movie>) = repository.saveLocalMovies(ArrayList(movies.map { DataMapper.Movies.toLocal(it) }))
}