package com.github.grupo6cineview.cineview.features.search.data.model

data class Search(
    val page: Int?,
    val results: List<SearchResult>,
    val total_pages: Int?,
    val total_results: Int?
)