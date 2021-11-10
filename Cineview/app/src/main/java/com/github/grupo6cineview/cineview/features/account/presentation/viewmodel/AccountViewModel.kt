package com.github.grupo6cineview.cineview.features.account.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.github.grupo6cineview.cineview.features.account.domain.AccountUseCase

class AccountViewModel(
    private val accountUseCase: AccountUseCase
) : ViewModel() {

    suspend fun getFavoritesSize() = accountUseCase.getFavoritesSize()
}