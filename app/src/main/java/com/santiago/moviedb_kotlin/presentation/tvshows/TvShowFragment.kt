package com.santiago.moviedb_kotlin.presentation.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.santiago.moviedb_kotlin.R
import com.santiago.moviedb_kotlin.databinding.ContentLoaderBinding
import com.santiago.moviedb_kotlin.databinding.FragmentTvShowsBinding
import com.santiago.moviedb_kotlin.domain.models.TvShow
import com.santiago.moviedb_kotlin.presentation.MainActivity
import com.santiago.moviedb_kotlin.utils.ViewHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment: Fragment() {

    private val tvShowViewModel: TvShowViewModel by viewModels()
    private lateinit var binding: FragmentTvShowsBinding
    private lateinit var loaderBinding: ContentLoaderBinding
    lateinit var adapter: TvShowsAdapter
    private lateinit var parent : MainActivity
    private var isLoading = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        loaderBinding = ContentLoaderBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parent = requireActivity() as MainActivity
        setUpTvShowsAdapter()
        setUpTvShowsObserver()

        if(parent.tvShows.isEmpty()) {
            isLoading(true)
            tvShowViewModel.getPopularTvShows()
        } else {
            adapter.addTvShows(parent.tvShows)
        }
    }

    private fun setUpTvShowsObserver() {
        tvShowViewModel.tvShowsLiveData.observe(requireActivity(), Observer {
            if(it != null && it.isNotEmpty()) {
                parent.tvShows.addAll(it)
                adapter.addTvShows(it)
            }
            isLoading(false)
            isLoadingMore(false)
        })
    }

    private fun setUpTvShowsAdapter() {
        adapter = TvShowsAdapter(requireContext(), object : TvShowsAdapter.Listener {
            override fun onTvShowClicked(tvShow: TvShow) {
                val bundle = Bundle().apply {
                    putInt("itemType", 1)
                    putInt("itemId", tvShow.id)
                }
                binding.tvShowsRecyclerView.findNavController().navigate(R.id.action_tvShowFragment_to_detailFragment, bundle)
            }
        })
        binding.tvShowsRecyclerView.adapter = adapter

        ViewHelper.setLoadMoreListener(binding.tvShowsRecyclerView) {
            if(!isLoading) {
                isLoadingMore(true)
                //TODO load next page
            }

        }
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