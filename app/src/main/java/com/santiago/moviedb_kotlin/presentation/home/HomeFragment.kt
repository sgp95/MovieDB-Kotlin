package com.santiago.moviedb_kotlin.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.santiago.moviedb_kotlin.R
import com.santiago.moviedb_kotlin.databinding.FragmentHomeBinding
import com.santiago.moviedb_kotlin.domain.models.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpMoviesAdapter()
        setUpMoviesObserver()
        homeViewModel.getPopularMovies()
    }

    private fun setUpMoviesObserver() {
        homeViewModel.moviesLiveData.observe(requireActivity(), Observer {
            if(it != null && it.isNotEmpty()) {
                adapter.addMovies(it)
            }
        })
    }

    private fun setUpMoviesAdapter() {
        adapter = MoviesAdapter(requireActivity(), object : MoviesAdapter.Listener {
            override fun onMovieClicked(movie: Movie) {
                val bundle = Bundle().apply {
                    putInt("itemType", 0)
                    putInt("itemId", movie.id)
                }
                binding.moviesRecyclerView.findNavController().navigate(R.id.action_fragmentHome_to_detailFragment, bundle)
            }
        })
        binding.moviesRecyclerView.adapter = adapter
    }
}