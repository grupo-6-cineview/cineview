package com.github.grupo6cineview.cineview.features.search.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class SearchResult(
    val id: Int,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    val overview: String
) {
    companion object {
        val DIFF_SEARCH = object : DiffUtil.ItemCallback<SearchResult>() {
            override fun areItemsTheSame(
                oldItem: SearchResult,
                newItem: SearchResult
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: SearchResult,
                newItem: SearchResult
            ): Boolean = oldItem.id == newItem.id
        }
    }
}