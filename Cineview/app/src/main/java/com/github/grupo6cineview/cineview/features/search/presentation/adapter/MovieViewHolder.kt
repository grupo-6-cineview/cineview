package com.github.grupo6cineview.cineview.features.search.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.github.grupo6cineview.cineview.databinding.MovieItemBinding
import com.github.grupo6cineview.cineview.features.search.data.model.recyclerview.MovieModel

class MovieViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieModel) {
        with(binding) {
            ivMovieItemPoster.setImageResource(movie.posterId)
            ivMovieItemPoster.clipToOutline = true
            tvMovieItemTitle.text = movie.title
        }
    }

}