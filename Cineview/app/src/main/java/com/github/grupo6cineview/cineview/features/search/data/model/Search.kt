package com.github.grupo6cineview.cineview.features.search.data.model

import com.google.gson.annotations.SerializedName

data class Search(
    val page: Int?,
    val results: List<SearchResult>,
    val total_pages: Int?,
    val total_results: Int?
)