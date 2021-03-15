package com.santiago.moviedb_kotlin.presentation.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.moviedb_kotlin.domain.models.Movie
import com.santiago.moviedb_kotlin.domain.usecases.movies.GetRemoteMovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val getRemoteMovieDetail: GetRemoteMovieDetail): ViewModel() {
    val movieDetailLiveData = MutableLiveData<Movie>()

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            try {
                movieDetailLiveData.postValue(getRemoteMovieDetail.execute(movieId))
            } catch (e: Exception) {
                Log.e("rastro", e.localizedMessage, e.cause)
                movieDetailLiveData.postValue(null)
            }
        }
    }
}