package com.github.grupo6cineview.cineview.features.movie.data.model.similar

data class SimilarResponse(
    val page: Int,
    val results: List<SimilarResult>
)