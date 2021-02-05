package com.santiago.moviedb_kotlin.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.santiago.moviedb_kotlin.R
import com.santiago.moviedb_kotlin.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("rastro", "HomeFragment - onCreateView")
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("rastro", "HomeFragment - onViewCreated")
        setUpMoviesObserver()
        homeViewModel.getPopularMovies()

        binding.testButton.setOnClickListener { button ->
            val bundle = Bundle().apply {
                putInt("itemType", 0)
                putInt("itemId", 1000)
            }
            button.findNavController().navigate(R.id.action_fragmentHome_to_detailFragment, bundle)
        }
    }

    private fun setUpMoviesObserver() {
        homeViewModel.moviesLiveData.observe(requireActivity(), {
            if(it != null && it.isNotEmpty()) {
                Log.d("rastro", "HomeFragment - movies loaded")
            }
        })
    }
}