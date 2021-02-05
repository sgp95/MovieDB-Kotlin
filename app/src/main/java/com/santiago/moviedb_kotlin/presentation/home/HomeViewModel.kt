package com.santiago.moviedb_kotlin.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.moviedb_kotlin.domain.models.Movie
import com.santiago.moviedb_kotlin.domain.usecases.movies.GetRemotePopularMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getRemotePopularMovies: GetRemotePopularMovies): ViewModel() {
    val moviesLiveData = MutableLiveData<ArrayList<Movie>>()

    fun getPopularMovies() {
        viewModelScope.launch {
            try {
                moviesLiveData.postValue(getRemotePopularMovies.execute())
            } catch (e: Exception) {
                Log.e("rastro", e.localizedMessage, e.cause)
                moviesLiveData.postValue(null)
            }
        }
    }
}