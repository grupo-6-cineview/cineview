package com.github.grupo6cineview.cineview.features.movie.data.repository

import com.github.grupo6cineview.cineview.api.ApiService
import com.github.grupo6cineview.cineview.base.BaseRepository
import com.github.grupo6cineview.cineview.db.dao.FavoriteDao
import com.github.grupo6cineview.cineview.db.dao.HomeDao
import com.github.grupo6cineview.cineview.db.entity.favorite.CastEntity
import com.github.grupo6cineview.cineview.db.entity.favorite.FavoriteEntity
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

    suspend fun deleteFavoriteDetails(favoriteId: Int) = favoriteDao.deleteFavorites(favoriteId)

    suspend fun deleteFavoriteCasts(castId: Int) = favoriteDao.deleteCasts(castId)

    suspend fun deleteFavoriteSimilars(similarId: Int) = favoriteDao.deleteSimilars(similarId)

    /**
     * Room - Home
     */
    suspend fun getCarouselMovie(movieId: Int) = homeDao.getCarouselMovie(movieId)

    suspend fun getNowPlayingMovie(movieId: Int) = homeDao.getNowPlayingMovie(movieId)

    suspend fun getTopRatedMovie(movieId: Int) = homeDao.getTopRatedMovie(movieId)

    suspend fun getPopularMovie(movieId: Int) = homeDao.getPopularMovie(movieId)

    suspend fun getTrendingMovie(movieId: Int) = homeDao.getTrendingMovie(movieId)
}