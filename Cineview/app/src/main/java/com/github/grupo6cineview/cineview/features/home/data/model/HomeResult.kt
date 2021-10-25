package com.github.grupo6cineview.cineview.features.home.data.model

import com.google.gson.annotations.SerializedName

data class HomeResult(
    val id: Int,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    val overview: String,
    val title: String,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("vote_average")
    val voteAverage: Double
)