package com.github.grupo6cineview.cineview.features.favorite.data.repository

import com.github.grupo6cineview.cineview.db.dao.FavoriteDao

class FavoriteRepository(
    private val favoriteDao: FavoriteDao
) {

    suspend fun getFavorites() = favoriteDao.getFavorites()

    suspend fun getFavoriteWithCasts(movieId: Int) = favoriteDao.getFavoriteWithCasts(movieId)

    suspend fun getFavoriteWithSimilars(movieId: Int) = favoriteDao.getFavoriteWithSimilars(movieId)

    suspend fun deleteFavoriteDetails(favoriteId: Int) = favoriteDao.deleteFavorites(favoriteId)

    suspend fun deleteFavoriteCasts(castId: Int) = favoriteDao.deleteCasts(castId)

    suspend fun deleteFavoriteSimilars(similarId: Int) = favoriteDao.deleteSimilars(similarId)
}