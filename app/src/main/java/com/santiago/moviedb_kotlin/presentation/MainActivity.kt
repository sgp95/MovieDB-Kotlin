package com.santiago.moviedb_kotlin.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.santiago.moviedb_kotlin.R
import com.santiago.moviedb_kotlin.databinding.ActivityMainBinding
import com.santiago.moviedb_kotlin.domain.models.Movie
import com.santiago.moviedb_kotlin.domain.models.TvShow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val movies = ArrayList<Movie>()
    val tvShows = ArrayList<TvShow>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpBottomNavigationController()
    }

    private fun setUpBottomNavigationController() {
        val navController = Navigation.findNavController(this, R.id.navHostFragmentContainer)
        NavigationUI.setupWithNavController(binding.bottomNavigationBar, navController)
    }
}