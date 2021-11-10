package com.github.grupo6cineview.cineview.features.movie.data.model.similar

import com.google.gson.annotations.SerializedName

data class SimilarResult(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    val overview: String,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("poster_path")
    var posterPath: String?,
    val title: String
)