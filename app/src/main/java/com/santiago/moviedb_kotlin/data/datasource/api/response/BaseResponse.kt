package com.santiago.moviedb_kotlin.data.datasource.api.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("page") var page: Int,
    @SerializedName("results") var results: ArrayList<T>,
    @SerializedName("total_results") var totalResults: Int,
    @SerializedName("total_pages") var totalPages: Int
)
