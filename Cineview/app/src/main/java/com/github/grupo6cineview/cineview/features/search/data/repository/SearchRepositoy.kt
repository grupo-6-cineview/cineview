package com.github.grupo6cineview.cineview.features.search.data.repository

import com.github.grupo6cineview.cineview.api.ApiService
import com.github.grupo6cineview.cineview.base.BaseRepository
import com.github.grupo6cineview.cineview.utils.ResponseApi

class SearchRepositoy : BaseRepository() {

    suspend fun getSearchResult(search: String, page: Int) : ResponseApi =
        safeApiCall {
            ApiService.tmdbApi.getSearchResult(search, page)
        }
}