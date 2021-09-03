package com.github.grupo6cineview.cineview.features.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.MovieItemBinding
import com.github.grupo6cineview.cineview.datamodel.SearchTrendingResult

class SearchViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: SearchTrendingResult, onClick: () -> Unit) {
        with(binding) {

            Glide.with(itemView.context)
                .load(movie.posterPath)
                .centerCrop()
                .placeholder(R.drawable.mortal_kombat_poster)
                .into(ivMovieItemPoster)

            ivMovieItemPoster.clipToOutline = true

            if (movie.mediaType == "tv") {
                tvMovieItemTitle.text = movie.name
            } else {
                tvMovieItemTitle.text = movie.title
            }

            cvMovieItemMovie.setOnClickListener {
                onClick()

                cvMovieItemMovie.requestFocusFromTouch()
            }
        }
    }

}