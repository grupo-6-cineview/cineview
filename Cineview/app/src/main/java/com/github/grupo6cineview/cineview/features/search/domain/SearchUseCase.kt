package com.github.grupo6cineview.cineview.features.search.domain

import com.github.grupo6cineview.cineview.features.search.data.mapper.SearchMapper
import com.github.grupo6cineview.cineview.features.search.data.model.SearchResult
import com.github.grupo6cineview.cineview.features.search.data.repository.SearchRepository

class SearchUseCase(
    private val searchRepository: SearchRepository,
    private val searchMapper: SearchMapper
) {

    suspend fun getMovieBySearch(search: String, page: Int): List<SearchResult>? =
        searchRepository.getSearchResult(search, page).let { response ->
            searchMapper.filterMoviesToSearch(response, search == "")
        }
}