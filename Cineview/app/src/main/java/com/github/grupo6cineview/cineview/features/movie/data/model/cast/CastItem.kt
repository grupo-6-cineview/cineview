package com.github.grupo6cineview.cineview.features.movie.data.model.cast

import androidx.recyclerview.widget.DiffUtil
import com.github.grupo6cineview.cineview.db.entity.favorite.CastEntity

data class CastItem(
    val castId: Int,
    val poster: String?,
    val name: String,
    val character: String,
    val movieRelatedId: Int
) {

    fun toCastEntity() =
        CastEntity(
            castId = castId,
            originalName = name,
            profilePath = poster ?: "",
            character = character,
            movieRelatedId = movieRelatedId
        )

    companion object {
        val DIFF_CAST = object : DiffUtil.ItemCallback<CastItem>() {
            override fun areItemsTheSame(
                oldItem: CastItem,
                newItem: CastItem
            ): Boolean = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: CastItem,
                newItem: CastItem
            ): Boolean = oldItem.character == newItem.character
        }
    }
}
