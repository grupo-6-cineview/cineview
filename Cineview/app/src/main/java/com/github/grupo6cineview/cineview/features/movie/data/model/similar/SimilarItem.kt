package com.github.grupo6cineview.cineview.features.movie.data.model.similar

import androidx.recyclerview.widget.DiffUtil
import com.github.grupo6cineview.cineview.db.entity.favorite.SimilarEntity

data class SimilarItem(
    val similarId: Int,
    val poster: String?,
    val title: String,
    val releaseYear: String,
    val genres: String,
    val movieRelatedId: Int
) {

    fun toSimilarEntity() =
        SimilarEntity(
            similarId = similarId,
            poster = poster ?: "",
            title = title,
            releaseYear = releaseYear,
            genres = genres,
            movieRelatedId = movieRelatedId
        )

    companion object {
        val DIFF_SIMILAR = object : DiffUtil.ItemCallback<SimilarItem>() {
            override fun areItemsTheSame(
                oldItem: SimilarItem,
                newItem: SimilarItem
            ): Boolean = oldItem.similarId == newItem.similarId

            override fun areContentsTheSame(
                oldItem: SimilarItem,
                newItem: SimilarItem
            ): Boolean = oldItem.title == newItem.title
        }
    }
}