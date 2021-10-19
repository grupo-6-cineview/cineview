package com.github.grupo6cineview.cineview.features.movie.data.model.cast

import androidx.recyclerview.widget.DiffUtil

data class CastItem(
    val poster: String?,
    val name: String,
    val character: String
) {

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
