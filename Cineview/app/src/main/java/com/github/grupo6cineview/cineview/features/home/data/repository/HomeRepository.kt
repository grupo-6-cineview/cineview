package com.github.grupo6cineview.cineview.features.home.data.repository

import com.github.grupo6cineview.cineview.api.ApiService
import com.github.grupo6cineview.cineview.extensions.BaseRepository
import com.github.grupo6cineview.cineview.extensions.ResponseApi


class HomeRepository : BaseRepository() {

    suspend fun getNowPlayingMovies(): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getNowPlayingMovies()
        }
    }

    suspend fun getTrendingMovies(mediaType: String, timeWindow: String, page: Int): ResponseApi =
        safeApiCall {
            ApiService.tmdbApi.getTrending(mediaType, timeWindow, page)
        }

}