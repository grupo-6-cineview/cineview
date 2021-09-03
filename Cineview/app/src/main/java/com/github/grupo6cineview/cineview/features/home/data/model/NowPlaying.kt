package com.github.grupo6cineview.cineview.features.home.data.model

data class NowPlaying(
    val dates: Dates,
    val page: Int,
    val results: List<NowPlayngResult>,
    val total_pages: Int,
    val total_results: Int
)