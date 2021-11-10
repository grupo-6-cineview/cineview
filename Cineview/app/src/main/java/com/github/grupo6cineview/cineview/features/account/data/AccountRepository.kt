package com.github.grupo6cineview.cineview.features.account.data

import com.github.grupo6cineview.cineview.db.dao.FavoriteDao
import kotlinx.coroutines.flow.flow

class AccountRepository(
    private val favoriteDao: FavoriteDao
) {

    suspend fun getFavorites() = flow {
        emit(favoriteDao.getFavorites().size)
    }
}