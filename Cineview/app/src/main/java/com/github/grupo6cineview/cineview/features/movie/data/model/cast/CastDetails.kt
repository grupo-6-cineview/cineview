package com.github.grupo6cineview.cineview.features.movie.data.model.cast

import com.google.gson.annotations.SerializedName

data class CastDetails(
    val id: Int,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("profile_path")
    val profilePath: String?,
    val character: String
)