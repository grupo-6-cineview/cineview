package com.github.grupo6cineview.cineview.features.favorite.data.model

import androidx.recyclerview.widget.DiffUtil

data class FavoriteViewParams(
    val movieId: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val voteAverage: String
) {

    companion object {
        val FAVORITE_DIFF = object : DiffUtil.ItemCallback<FavoriteViewParams>() {
            override fun areItemsTheSame(
                oldItem: FavoriteViewParams,
                newItem: FavoriteViewParams
            ): Boolean = newItem.movieId == oldItem.movieId

            override fun areContentsTheSame(
                oldItem: FavoriteViewParams,
                newItem: FavoriteViewParams
            ): Boolean = newItem.movieId == oldItem.movieId
        }
    }
}
