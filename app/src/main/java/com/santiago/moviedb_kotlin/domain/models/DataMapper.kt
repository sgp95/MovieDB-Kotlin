package com.santiago.moviedb_kotlin.domain.models

import com.santiago.moviedb_kotlin.data.datasource.api.response.MovieResponse
import com.santiago.moviedb_kotlin.data.datasource.api.response.TvShowResponse
import com.santiago.moviedb_kotlin.data.datasource.room.entities.MovieEntity
import com.santiago.moviedb_kotlin.data.datasource.room.entities.TvShowEntity

class DataMapper {
    object Movies {
        fun fromRemote(response: MovieResponse) = Movie(
            id = response.id,
            title = response.title,
            description = response.overview,
            posterUrl = response.posterPath
        )

        fun fromLocal(entity: MovieEntity) = Movie(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            posterUrl = entity.posterUrl
        )

        fun toLocal(movie: Movie) = MovieEntity(
            id = movie.id,
            title = movie.title,
            description = movie.description,
            posterUrl = movie.posterUrl
        )
    }

    object TvShows {
        fun fromRemote(response: TvShowResponse) = TvShow(
            id = response.id,
            name = response.name,
            description = response.overview,
            posterUrl = response.posterPath
        )

        fun fromLocal(entity: TvShowEntity) = TvShow(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            posterUrl = entity.posterUrl
        )

        fun toLocal(tvShow: TvShow) = TvShowEntity(
            id = tvShow.id,
            name = tvShow.name,
            description = tvShow.description,
            posterUrl = tvShow.posterUrl
        )
    }
}