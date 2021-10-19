package com.github.grupo6cineview.cineview.features.movie.data.model.details

import androidx.recyclerview.widget.DiffUtil

data class DetailsItem(
    val title: String,
    val subtitle: String
) {

    companion object {
        val DIFF_DETAILS = object : DiffUtil.ItemCallback<DetailsItem>() {
            override fun areItemsTheSame(
                oldItem: DetailsItem,
                newItem: DetailsItem
            ): Boolean = oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: DetailsItem,
                newItem: DetailsItem
            ): Boolean = oldItem.subtitle == newItem.subtitle
        }
    }
}
