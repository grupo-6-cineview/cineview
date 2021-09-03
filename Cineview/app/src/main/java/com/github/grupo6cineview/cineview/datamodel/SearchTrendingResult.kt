package com.github.grupo6cineview.cineview.datamodel

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class SearchTrendingResult(
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
        var DIFF_CALBACK_SEARCH_TRENDING: DiffUtil.ItemCallback<SearchTrendingResult> =
            object : DiffUtil.ItemCallback<SearchTrendingResult>() {
                override fun areItemsTheSame(oldItem: SearchTrendingResult, newItem: SearchTrendingResult): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: SearchTrendingResult, newItem: SearchTrendingResult): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }

}