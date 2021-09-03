package com.github.grupo6cineview.cineview.datamodel

data class Trending(
    val page: Int,
    val results: List<SearchTrendingResult>,
    val total_pages: Int,
    val total_results: Int
)