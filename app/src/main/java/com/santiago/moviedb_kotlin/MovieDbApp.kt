package com.santiago.moviedb_kotlin

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieDbApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}