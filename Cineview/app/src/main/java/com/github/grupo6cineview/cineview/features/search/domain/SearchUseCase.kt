package com.github.grupo6cineview.cineview.features.search.domain

import com.github.grupo6cineview.cineview.extensions.ResponseApi
import com.github.grupo6cineview.cineview.extensions.getFullImageUrl
import com.github.grupo6cineview.cineview.features.search.data.model.Search
import com.github.grupo6cineview.cineview.features.search.data.repository.SearchRepositoy

class SearchUseCase {

    private val searchRepository = SearchRepositoy()

    suspend fun getSearchResult(search: String) : ResponseApi =
        searchRepository.getSearchResult(search).let { response ->
            when (response) {
                is ResponseApi.Success -> {
                    (response.data as Search).let { search ->
                        val resultList = search.results.map { result1 ->
                            result1.apply { posterPath = posterPath?.getFullImageUrl(200) }
                        }.filter { result2 ->
                            result2.mediaType == "tv" || result2.mediaType == "movie"
                        }

                        ResponseApi.Success(resultList)
                    }
                }

                is ResponseApi.Error -> response
            }
        }

}