package com.santiago.moviedb_kotlin.domain.usecases.tvshows

import com.santiago.moviedb_kotlin.data.repository.TvShowRepository
import com.santiago.moviedb_kotlin.domain.models.DataMapper
import com.santiago.moviedb_kotlin.domain.models.TvShow
import javax.inject.Inject

class SaveLocalTvShows @Inject constructor(private val repository: TvShowRepository) {
    suspend fun execute(tvShows: ArrayList<TvShow>) = repository.saveLocalTvShows(ArrayList(tvShows.map { DataMapper.TvShows.toLocal(it) }))
}