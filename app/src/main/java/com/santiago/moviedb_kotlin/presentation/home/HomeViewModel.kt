package com.santiago.moviedb_kotlin.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.moviedb_kotlin.domain.models.Movie
import com.santiago.moviedb_kotlin.domain.usecases.movies.GetLocalPopularMovies
import com.santiago.moviedb_kotlin.domain.usecases.movies.GetRemotePopularMovies
import com.santiago.moviedb_kotlin.domain.usecases.movies.SaveLocalMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getRemotePopularMovies: GetRemotePopularMovies, private val getLocalPopularMovies: GetLocalPopularMovies, private val saveLocalMovies: SaveLocalMovies): ViewModel() {
    val moviesLiveData = MutableLiveData<ArrayList<Movie>>()

    fun getSavedPopularMovies() {
        viewModelScope.launch {
            try {
                val movies = getLocalPopularMovies.execute()
                if(!movies.isNullOrEmpty())
                    moviesLiveData.postValue(movies)
                else
                    getRemotePopularMovies()
            } catch (e: Exception) {
                Log.e("rastro", e.localizedMessage, e.cause)
                moviesLiveData.postValue(null)
            }
        }
    }

    fun getRemotePopularMovies() {
        viewModelScope.launch {
            try {
                val movies = getRemotePopularMovies.execute()
                moviesLiveData.postValue(movies)
                saveLocalMovies.execute(movies)
            } catch (e: Exception) {
                Log.e("rastro", e.localizedMessage, e.cause)
                moviesLiveData.postValue(null)
            }
        }
    }
}