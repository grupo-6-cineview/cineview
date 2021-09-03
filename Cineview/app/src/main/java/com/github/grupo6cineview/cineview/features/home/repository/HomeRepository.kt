package com.github.grupo6cineview.cineview.features.home.repository

import com.github.grupo6cineview.cineview.api.ApiService
import com.github.grupo6cineview.cineview.extensions.BaseRepository
import com.github.grupo6cineview.cineview.extensions.ResponseApi


class HomeRepository : BaseRepository() {

    suspend fun getNowPlayingMovies(): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getNowPlayingMovies()
        }
    }

    suspend fun getTrendingMovies(mediaType: String, timeWindow: String): ResponseApi =
        safeApiCall {
            ApiService.tmdbApi.getTrendingMoviesDay(mediaType, timeWindow)
        }

}