package com.github.grupo6cineview.cineview.features.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.MovieItemBinding
import com.github.grupo6cineview.cineview.datamodel.SearchTrendingResult

class SearchViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: SearchTrendingResult, onClick: (id: Int, mediaType: String) -> Unit) {
        with(binding) {

            Glide.with(itemView.context)
                .load(movie.posterPath)
                .centerCrop()
                .placeholder(R.drawable.no_poster_path)
                .into(ivMovieItemPoster)

            ivMovieItemPoster.clipToOutline = true

            if (movie.mediaType == "tv") {
                tvMovieItemTitle.text = movie.name
            } else {
                tvMovieItemTitle.text = movie.title
            }

            cvMovieItemMovie.setOnClickListener {
                onClick(movie.id, movie.mediaType)

                cvMovieItemMovie.requestFocusFromTouch()
            }
        }
    }

}