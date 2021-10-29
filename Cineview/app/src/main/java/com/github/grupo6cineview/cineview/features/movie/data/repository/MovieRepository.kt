package com.github.grupo6cineview.cineview.features.movie.data.repository

import com.github.grupo6cineview.cineview.api.ApiService
import com.github.grupo6cineview.cineview.base.BaseRepository
import com.github.grupo6cineview.cineview.db.dao.FavoriteDao
import com.github.grupo6cineview.cineview.db.dao.HomeDao
import com.github.grupo6cineview.cineview.utils.ResponseApi

class MovieRepository(
    private val favoriteDao: FavoriteDao,
    private val homeDao: HomeDao
) : BaseRepository() {

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