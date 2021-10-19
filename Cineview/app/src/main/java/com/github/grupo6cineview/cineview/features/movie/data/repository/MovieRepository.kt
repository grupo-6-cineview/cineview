package com.github.grupo6cineview.cineview.features.movie.data.repository

import com.github.grupo6cineview.cineview.api.ApiService
import com.github.grupo6cineview.cineview.extensions.BaseRepository
import com.github.grupo6cineview.cineview.extensions.ResponseApi

class MovieRepository : BaseRepository() {

    suspend fun getAllGenres(): ResponseApi =
        safeApiCall {
            ApiService.tmdbApi.getAllGenres()
        }

    suspend fun getMovieDetails(id: Int): ResponseApi =
        safeApiCall {
            ApiService.tmdbApi.getMovieDetails(id)
        }

    suspend fun getMovieCast(id: Int): ResponseApi =
        safeApiCall {
            ApiService.tmdbApi.getMovieCast(id)
        }

    suspend fun getSimilarMovies(id: Int): ResponseApi =
        safeApiCall {
            ApiService.tmdbApi.getSimilarMovies(id)
        }
}