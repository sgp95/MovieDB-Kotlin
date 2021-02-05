package com.santiago.moviedb_kotlin.data.datasource.api.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
        @SerializedName("id") var id: Int,
        @SerializedName("name") var name: String,
        @SerializedName("overview") var overview: String,
        @SerializedName("poster_path") var posterPath: String
)
