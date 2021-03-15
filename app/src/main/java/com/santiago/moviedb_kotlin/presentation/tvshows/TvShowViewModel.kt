package com.santiago.moviedb_kotlin.presentation.tvshows

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.moviedb_kotlin.domain.models.TvShow
import com.santiago.moviedb_kotlin.domain.usecases.tvshows.GetLocalPopularTvShows
import com.santiago.moviedb_kotlin.domain.usecases.tvshows.GetRemotePopularTvShows
import com.santiago.moviedb_kotlin.domain.usecases.tvshows.SaveLocalTvShows
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(private val getRemotePopularTvShows: GetRemotePopularTvShows, private val getLocalPopularTvShows: GetLocalPopularTvShows, private val saveLocalTvShows: SaveLocalTvShows): ViewModel() {
    val tvShowsLiveData = MutableLiveData<ArrayList<TvShow>>()

    fun getSavedPopularTvShows() {
        viewModelScope.launch {
            try {
                val tvShows = getLocalPopularTvShows.execute()
                if(!tvShows.isNullOrEmpty())
                    tvShowsLiveData.postValue(tvShows)
                else
                    getPopularTvShows()
            } catch (e: Exception) {
                Log.e("rastro", e.localizedMessage, e.cause)
                tvShowsLiveData.postValue(null)
            }
        }
    }

    fun getPopularTvShows() {
        viewModelScope.launch {
            try {
                val tvShows = getRemotePopularTvShows.execute()
                tvShowsLiveData.postValue(tvShows)
                saveLocalTvShows.execute(tvShows)
            } catch (e: Exception) {
                Log.e("rastro", e.localizedMessage, e.cause)
                tvShowsLiveData.postValue(null)
            }
        }
    }
}