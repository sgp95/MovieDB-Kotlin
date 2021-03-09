package com.santiago.moviedb_kotlin.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val id: Int,
    val name: String,
    val description: String,
    val posterUrl: String
) : Parcelable
