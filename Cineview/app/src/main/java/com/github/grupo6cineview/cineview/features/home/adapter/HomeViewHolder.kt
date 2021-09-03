package com.github.grupo6cineview.cineview.features.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.MovieItemBinding
import com.github.grupo6cineview.cineview.datamodel.SearchTrendingResult

class HomeViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: SearchTrendingResult?, onClick: (id: Int, mediaType: String) -> Unit) {
        movie?.let { movieNonNull ->
            with(binding) {

                Glide.with(itemView.context)
                    .load(movieNonNull.posterPath)
                    .centerCrop()
                    .placeholder(R.drawable.no_image)
                    .into(ivMovieItemPoster)

                ivMovieItemPoster.clipToOutline = true

                if (movieNonNull.mediaType == "tv") {
                    tvMovieItemTitle.text = movieNonNull.name
                } else {
                    tvMovieItemTitle.text = movieNonNull.title
                }

                cvMovieItemMovie.setOnClickListener {
                    onClick(movieNonNull.id, movieNonNull.mediaType)

                    cvMovieItemMovie.requestFocusFromTouch()
                }
            }
        }
    }

}