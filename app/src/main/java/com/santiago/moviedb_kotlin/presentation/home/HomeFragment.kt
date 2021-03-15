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
import com.santiago.moviedb_kotlin.databinding.ContentLoaderBinding
import com.santiago.moviedb_kotlin.databinding.FragmentHomeBinding
import com.santiago.moviedb_kotlin.domain.models.Movie
import com.santiago.moviedb_kotlin.presentation.MainActivity
import com.santiago.moviedb_kotlin.utils.ViewHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var loaderBinding: ContentLoaderBinding
    private lateinit var adapter: MoviesAdapter
    private lateinit var parent : MainActivity
    private var isLoading = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        loaderBinding = ContentLoaderBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parent = requireActivity() as MainActivity
        setUpMoviesAdapter()
        setUpMoviesObserver()

        if(parent.movies.isEmpty()) {
            isLoading(true)
            homeViewModel.getRemotePopularMovies()
        } else {
            adapter.addMovies(parent.movies)
        }
    }

    private fun setUpMoviesObserver() {
        homeViewModel.moviesLiveData.observe(requireActivity(), Observer {
            if(it != null && it.isNotEmpty()) {
                parent.movies.addAll(it)
                adapter.addMovies(it)
            }
            isLoading(false)
            isLoadingMore(false)

            ViewHelper.setLoadMoreListener(binding.moviesRecyclerView, parent.movies.size-1) {
                if(!isLoading) {
                    isLoadingMore(true)
                    Log.d("rastro", "Load more movies")
                }
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

    private fun isLoading(isLoading: Boolean) {
        this.isLoading = isLoading
        loaderBinding.loaderContainer.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    private fun isLoadingMore(isLoadingMore: Boolean) {
        isLoading = isLoadingMore
        binding.loadingMoreBar.visibility = if(isLoadingMore) View.VISIBLE else View.GONE
    }
}