package com.github.grupo6cineview.cineview.datamodel

data class Search(
    val page: Int?,
    val results: List<SearchTrendingResult>,
    val total_pages: Int?,
    val total_results: Int?
)