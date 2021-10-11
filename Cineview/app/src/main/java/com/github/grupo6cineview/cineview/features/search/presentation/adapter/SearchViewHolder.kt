package com.github.grupo6cineview.cineview.features.search.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.MovieItemBinding
import com.github.grupo6cineview.cineview.features.search.data.model.SearchResult

class SearchViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: SearchResult, onClick: (id: Int, mediaType: String) -> Unit) {
        with(binding) {

            Glide.with(itemView.context)
                .load(movie.posterPath)
                .centerCrop()
                .placeholder(R.drawable.no_poster_path)
                .into(ivMovieItemPoster)

            ivMovieItemPoster.clipToOutline = true

            ivMovieItemPoster.setOnClickListener {
                onClick(movie.id, movie.mediaType)
            }
        }
    }

}