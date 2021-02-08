package com.santiago.moviedb_kotlin.presentation.tvshows

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.moviedb_kotlin.domain.models.TvShow
import com.santiago.moviedb_kotlin.domain.usecases.tvshows.GetRemotePopularTvShows
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(private val getRemotePopularTvShows: GetRemotePopularTvShows): ViewModel() {
    val tvShowsLiveData = MutableLiveData<ArrayList<TvShow>>()

    fun getPopularTvShows() {
        viewModelScope.launch {
            try {
                tvShowsLiveData.postValue(getRemotePopularTvShows.execute())
            } catch (e: Exception) {
                Log.e("rastro", e.localizedMessage, e.cause)
                tvShowsLiveData.postValue(null)
            }
        }
    }
}