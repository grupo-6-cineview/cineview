package com.github.grupo6cineview.cineview.features.movie.movie.data.repository

import com.github.grupo6cineview.cineview.api.ApiService
import com.github.grupo6cineview.cineview.extensions.BaseRepository
import com.github.grupo6cineview.cineview.extensions.ResponseApi

class MovieRepository : BaseRepository() {

    suspend fun getMovieDetails(id: Int): ResponseApi =
        safeApiCall {
            ApiService.tmdbApi.getMovieDetails(id)
        }

    suspend fun getTvDetails(id: Int): ResponseApi =
        safeApiCall {
            ApiService.tmdbApi.getTvDetails(id)
        }

}