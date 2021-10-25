package com.github.grupo6cineview.cineview.features.home.data.model

import androidx.recyclerview.widget.DiffUtil

data class HomeViewParams(
    val id: Int,
    var posterPath: String,
    var backdropPath: String,
    val overview: String,
    val title: String,
    val voteCount: String,
    val voteAverage: String
) {
    companion object {

        val HOME_RESULT_DIFF = object : DiffUtil.ItemCallback<HomeViewParams>() {

            override fun areItemsTheSame(
                oldItem: HomeViewParams,
                newItem: HomeViewParams
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: HomeViewParams,
                newItem: HomeViewParams
            ): Boolean = oldItem.id == newItem.id
        }
    }
}