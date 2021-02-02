package com.santiago.moviedb_kotlin.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.santiago.moviedb_kotlin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpMoviesObserver()
        mainViewModel.getPopularMovies()
    }

    private fun setUpMoviesObserver() {
        mainViewModel.moviesLiveData.observe(this, Observer {
            if(it == null) {
                binding.testLabel.text = "Request failed"
            } else {
                it.forEach { movie ->
                    Log.d("rastro", "Movie Title: ${movie.title}")
                }
            }
        })
    }
}