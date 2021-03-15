package com.santiago.moviedb_kotlin.presentation.detail

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.santiago.moviedb_kotlin.BuildConfig
import com.santiago.moviedb_kotlin.R
import com.santiago.moviedb_kotlin.databinding.ContentLoaderBinding
import com.santiago.moviedb_kotlin.databinding.FragmentDetailBinding
import com.santiago.moviedb_kotlin.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    companion object {
        const val ITEM_MOVIE = 0
        const val ITEM_TV_SHOW = 1
    }

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailBinding
    private lateinit var loaderBinding: ContentLoaderBinding
    private var itemType = -1
    private var itemId = -1
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        loaderBinding = ContentLoaderBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            itemType = it.getInt("itemType")
            itemId = it.getInt("itemId")
        }

        isLoading(true)
        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)

        setUpMovieDetailObserver()

        movieDetailViewModel.getMovieDetail(itemId)
//        binding.collapsingToolbarLayout.title = "Item detail"
//        Handler().postDelayed({
//            binding.collapsingToolbarLayout.setContentScrimColor(resources.getColor(R.color.purple_200))
//        }, 5000)
    }

    private fun setUpMovieDetailObserver() {
        movieDetailViewModel.movieDetailLiveData.observe(requireActivity(), {
            if(it == null) return@observe
            binding.collapsingToolbarLayout.title = it.title
            binding.description.text = it.description
            Glide.with(this)
                .asBitmap()
                .load("${BuildConfig.POSTER_BIG_URL}${it.posterUrl}")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        isLoading(false)
                        return false
                    }

                    override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        resource?.let { bitmap ->
                            val palette = Palette.from(bitmap).generate()
                            val color = palette.getDominantColor(resources.getColor(R.color.purple_700))
                            binding.collapsingToolbarLayout.setContentScrimColor(color)
                        }
                        isLoading(false)
                        return false
                    }
                })
                .into(binding.itemHeaderPoster)
        })
    }

    private fun isLoading(isLoading: Boolean) {
        this.isLoading = isLoading
        loaderBinding.loaderContainer.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}