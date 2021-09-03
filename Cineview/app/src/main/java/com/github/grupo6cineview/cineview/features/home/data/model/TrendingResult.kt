package com.github.grupo6cineview.cineview.features.home.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class TrendingResult(
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
        var DIFF_CALBACK_TRENDING: DiffUtil.ItemCallback<TrendingResult> =
            object : DiffUtil.ItemCallback<TrendingResult>() {
                override fun areItemsTheSame(oldItem: TrendingResult, newItem: TrendingResult): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: TrendingResult, newItem: TrendingResult): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }

}