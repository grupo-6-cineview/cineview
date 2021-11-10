package com.github.grupo6cineview.cineview.features.account.domain

import com.github.grupo6cineview.cineview.features.account.data.AccountRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single

class AccountUseCase(
    private val accountRepository: AccountRepository
) {

    suspend fun getFavoritesSize() = flow {
        emit(
            accountRepository.getFavorites().single()
        )
    }
}