package com.github.grupo6cineview.cineview.features.search.data.repository

import com.github.grupo6cineview.cineview.api.ApiService
import com.github.grupo6cineview.cineview.extensions.BaseRepository
import com.github.grupo6cineview.cineview.extensions.ResponseApi

class SearchRepositoy : BaseRepository() {

    suspend fun getSearchResult(search: String) : ResponseApi =
        safeApiCall {
            ApiService.tmdbApi.getSearchResult(search, 1)
        }

}