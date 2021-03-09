package com.santiago.moviedb_kotlin.domain.usecases.tvshows

import com.santiago.moviedb_kotlin.data.repository.TvShowRepository
import com.santiago.moviedb_kotlin.domain.models.DataMapper
import com.santiago.moviedb_kotlin.domain.models.TvShow
import javax.inject.Inject

class GetLocalPopularTvShows @Inject constructor(private val repository: TvShowRepository) {
    suspend fun execute(): ArrayList<TvShow> = ArrayList(repository.getLocalPopularTvShows().map {
        DataMapper.TvShows.fromLocal(it)
    })
}