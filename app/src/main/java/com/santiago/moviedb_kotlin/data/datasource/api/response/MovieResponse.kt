package com.santiago.moviedb_kotlin.data.datasource.api.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("poster_path") var posterPath: String
)
