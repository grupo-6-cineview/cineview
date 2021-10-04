package com.github.grupo6cineview.cineview.features.home.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class HomeResult(
    val id: Int,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    val overview: String,
    val title: String
) {

    companion object {

        val HOME_RESULT_DIFF = object : DiffUtil.ItemCallback<HomeResult>() {

            override fun areItemsTheSame(
                oldItem: HomeResult,
                newItem: HomeResult
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: HomeResult,
                newItem: HomeResult
            ): Boolean = oldItem.id == newItem.id
        }
    }
}
