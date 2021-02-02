package com.santiago.moviedb_kotlin.domain.models

import com.santiago.moviedb_kotlin.data.datasource.api.response.MovieResponse

class DataMapper {
    object Movies {
        fun fromRemote(response: MovieResponse) = Movie(
            id = response.id,
            title = response.title,
            description = response.overview,
            posterUrl = response.posterPath
        )
    }
}