package com.github.grupo6cineview.cineview.features.movie.data.model.cast

import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("id")
    val movieId: Int,
    val cast: List<CastDetails>
)