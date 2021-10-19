package com.github.grupo6cineview.cineview.features.movie.data.model.similar

import androidx.recyclerview.widget.DiffUtil

data class SimilarItem(
    val poster: String?,
    val title: String,
    val releaseYear: String,
    val genres: String
) {

    companion object {
        val DIFF_SIMILAR = object : DiffUtil.ItemCallback<SimilarItem>() {
            override fun areItemsTheSame(
                oldItem: SimilarItem,
                newItem: SimilarItem
            ): Boolean = oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: SimilarItem,
                newItem: SimilarItem
            ): Boolean = oldItem.releaseYear == newItem.releaseYear
        }
    }
}