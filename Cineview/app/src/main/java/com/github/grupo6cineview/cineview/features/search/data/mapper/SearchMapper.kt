package com.github.grupo6cineview.cineview.features.search.data.mapper

import com.github.grupo6cineview.cineview.extensions.ResponseApi
import com.github.grupo6cineview.cineview.extensions.getFullImageUrl
import com.github.grupo6cineview.cineview.features.search.data.model.SearchResponse
import com.github.grupo6cineview.cineview.features.search.data.model.SearchResult

class SearchMapper {

    fun filterMoviesToSearch(response: ResponseApi): List<SearchResult>? =
        when (response) {
            is ResponseApi.Success -> {
                (response.data as SearchResponse).let { searchResponse ->
                    searchResponse.results
                        .filter { searchResult ->
                            with(searchResult) {
                                posterPath != null &&
                                        backdropPath != null &&
                                        overview != ""
                            }
                        }
                        .map { searchResult ->
                            searchResult.apply {
                                posterPath = posterPath?.getFullImageUrl(200)
                                backdropPath = backdropPath?.getFullImageUrl(500)
                            }
                        }
                }
            }

            is ResponseApi.Error -> null
        }
}