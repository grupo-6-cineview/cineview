package com.github.grupo6cineview.cineview.features.search.data.model

data class SearchResponse(
    val page: Int,
    val results: List<SearchResult>
)