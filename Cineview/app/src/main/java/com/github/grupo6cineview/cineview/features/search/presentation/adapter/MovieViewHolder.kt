package com.github.grupo6cineview.cineview.features.search.presentation.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.github.grupo6cineview.cineview.databinding.FragmentMovieBinding
import com.github.grupo6cineview.cineview.databinding.MovieItemBinding
import com.github.grupo6cineview.cineview.features.movie.presentation.ui.MovieFragment
import com.github.grupo6cineview.cineview.features.search.data.model.recyclerview.MovieModel

class MovieViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieModel, onClick: () -> Unit) {
        with(binding) {
            ivMovieItemPoster.setImageResource(movie.posterId)
            ivMovieItemPoster.clipToOutline = true
            tvMovieItemTitle.text = movie.title

            cvMovieItemMovie.setOnClickListener {
                onClick()

                cvMovieItemMovie.requestFocusFromTouch()
            }
        }
    }

}