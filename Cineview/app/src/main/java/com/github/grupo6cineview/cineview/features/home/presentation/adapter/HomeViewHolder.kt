package com.github.grupo6cineview.cineview.features.home.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.MovieItemHomeBinding
import com.github.grupo6cineview.cineview.features.home.data.model.HomeViewParams

class HomeViewHolder(private val binding: MovieItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: HomeViewParams, onClick: (id: Int) -> Unit) {
        movie.run {
            with(binding) {
                Glide.with(itemView.context)
                    .load(posterPath)
                    .centerCrop()
                    .placeholder(R.drawable.no_poster_path)
                    .into(ivMovieItemPoster)

                ivMovieItemPoster.clipToOutline = true

                ivMovieItemPoster.setOnClickListener {
                    onClick(id)
                }
            }
        }
    }
}