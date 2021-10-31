package com.github.grupo6cineview.cineview.features.movie.data.repository

import com.github.grupo6cineview.cineview.api.ApiService
import com.github.grupo6cineview.cineview.base.BaseRepository
import com.github.grupo6cineview.cineview.db.dao.FavoriteDao
import com.github.grupo6cineview.cineview.db.dao.HomeDao
import com.github.grupo6cineview.cineview.db.entity.favorite.CastEntity
import com.github.grupo6cineview.cineview.db.entity.favorite.FavoriteEntity
import com.github.grupo6cineview.cineview.db.entity.favorite.FavoriteWithSimilar
import com.github.grupo6cineview.cineview.db.entity.favorite.SimilarEntity
import com.github.grupo6cineview.cineview.utils.ResponseApi

class MovieRepository(
    private val favoriteDao: FavoriteDao,
    private val homeDao: HomeDao
) : BaseRepository() {

    /**
     * API Calls
     */
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

    /**
     * Room - Favorite
     */
    suspend fun saveFavoriteDetails(favorite: FavoriteEntity) = favoriteDao.insertFavorite(favorite)

    suspend fun saveFavoriteCasts(casts: List<CastEntity>) = favoriteDao.insertCasts(casts)

    suspend fun saveFavoriteSimilars(similars: List<SimilarEntity>) = favoriteDao.insertSimilars(similars)

    suspend fun getFavoriteWithCasts(movieId: Int) = favoriteDao.getFavoriteWithCasts(movieId)

    suspend fun getFavoriteWithSimilars(movieId: Int) = favoriteDao.getFavoriteWithSimilars(movieId)

    suspend fun deleteFavoriteDetails(favorite: FavoriteEntity) = favoriteDao.deleteFavorites(favorite)

    suspend fun deleteFavoriteCasts(casts: List<CastEntity>) = favoriteDao.deleteCasts(casts)

    suspend fun deleteFavoriteSimilars(similars: List<SimilarEntity>) = favoriteDao.deleteSimilars(similars)
}