package com.github.grupo6cineview.cineview.features.home.data.model

data class Trending(
    val page: Int,
    val results: List<TrendingResult>,
    val total_pages: Int,
    val total_results: Int
)