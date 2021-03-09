package com.santiago.moviedb_kotlin.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val posterUrl: String
) : Parcelable