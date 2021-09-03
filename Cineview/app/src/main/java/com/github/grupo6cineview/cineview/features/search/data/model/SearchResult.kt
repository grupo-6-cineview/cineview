package com.github.grupo6cineview.cineview.features.search.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class SearchResult(
    val id: Int,
    @SerializedName("media_type")
    val mediaType: String,
    val name: String?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("poster_path")
    var posterPath: String?,
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?
) {

    companion object {
        var DIFF_CALBACK_SEARCH: DiffUtil.ItemCallback<SearchResult> =
            object : DiffUtil.ItemCallback<SearchResult>() {
                override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }

}