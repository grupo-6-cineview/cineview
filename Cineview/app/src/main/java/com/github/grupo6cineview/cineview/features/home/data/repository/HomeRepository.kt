package com.github.grupo6cineview.cineview.features.home.data.repository

import com.github.grupo6cineview.cineview.api.ApiService
import com.github.grupo6cineview.cineview.base.BaseRepository
import com.github.grupo6cineview.cineview.db.dao.HomeDao
import com.github.grupo6cineview.cineview.utils.ResponseApi


class HomeRepository(
    private val homeDao: HomeDao
) : BaseRepository() {

    suspend fun getNowPlayingMovies(page: Int): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getNowPlayingMovies(page)
        }
    }

    suspend fun getPopularMovies(page: Int): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getPopularMovies(page)
        }
    }

    suspend fun getTopRatedMovies(page: Int): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getTopRatedMovies(page)
        }
    }

    suspend fun getTrendingMovies(page: Int): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getTrendingMovies(page = page)
        }
    }
}