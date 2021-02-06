package com.santiago.moviedb_kotlin.presentation.tvshows

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santiago.moviedb_kotlin.BuildConfig
import com.santiago.moviedb_kotlin.databinding.ItemTvShowListBinding
import com.santiago.moviedb_kotlin.domain.models.TvShow

class TvShowsAdapter constructor(private val context: Context, private val listener: Listener): RecyclerView.Adapter<TvShowsAdapter.ViewHolder>() {

    val tvShows = ArrayList<TvShow>()

    inner class ViewHolder(val binding: ItemTvShowListBinding): RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onTvShowClicked(tvShow: TvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTvShowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(tvShows[position]) {
                binding.itemListTitle.text = name
                Glide.with(context)
                    .load("${BuildConfig.POSTER_SMALL_URL}$posterUrl")
                    .centerCrop()
                    .placeholder(ColorDrawable(Color.BLACK))
                    .into(binding.itemListPoster)
                binding.container.setOnClickListener {
                    listener.onTvShowClicked(this)
                }
            }
        }
    }

    override fun getItemCount() = tvShows.size

    fun addTvShows(movies: ArrayList<TvShow>) {
        val initialPosition = this.tvShows.size
        this.tvShows.addAll(movies)
        notifyItemRangeInserted(initialPosition, initialPosition + this.tvShows.size -1)
    }
}