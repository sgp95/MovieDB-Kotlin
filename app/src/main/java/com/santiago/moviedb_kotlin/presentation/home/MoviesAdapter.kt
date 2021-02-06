package com.santiago.moviedb_kotlin.presentation.home

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santiago.moviedb_kotlin.BuildConfig
import com.santiago.moviedb_kotlin.databinding.ItemListBinding
import com.santiago.moviedb_kotlin.domain.models.Movie

class MoviesAdapter constructor(private val context: Context, private val listener: Listener) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    val movies = ArrayList<Movie>()

    inner class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(movies[position]) {
                binding.itemListTitle.text = title
                binding.itemListDescription.text = description
                Glide.with(context)
                    .load("${BuildConfig.POSTER_SMALL_URL}$posterUrl")
                    .centerCrop()
                    .placeholder(ColorDrawable(Color.BLACK))
                    .into(binding.itemListPoster)
                binding.container.setOnClickListener {
                    listener.onMovieClicked(this)
                }
            }
        }
    }

    override fun getItemCount() = movies.size

    fun addMovies(movies: ArrayList<Movie>) {
        val initialPosition = this.movies.size
        this.movies.addAll(movies)
        notifyItemRangeInserted(initialPosition, initialPosition + this.movies.size -1)
    }

    interface Listener {
        fun onMovieClicked(movie: Movie)
    }
}