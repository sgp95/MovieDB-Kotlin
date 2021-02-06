package com.santiago.moviedb_kotlin.presentation.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.santiago.moviedb_kotlin.R
import com.santiago.moviedb_kotlin.databinding.FragmentTvShowsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment: Fragment() {

    private lateinit var binding: FragmentTvShowsBinding
    lateinit var adapter: TvShowsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.testButton.setOnClickListener { button ->
            val bundle = Bundle().apply {
                putInt("itemType", 1)
                putInt("itemId", 2000)
            }
            button.findNavController().navigate(R.id.action_tvShowFragment_to_detailFragment, bundle)
        }
    }
}