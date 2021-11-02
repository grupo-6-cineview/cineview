package com.github.grupo6cineview.cineview.features.favorite.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.grupo6cineview.cineview.base.BaseViewModel
import com.github.grupo6cineview.cineview.features.favorite.data.model.FavoriteViewParams
import com.github.grupo6cineview.cineview.features.favorite.domain.FavoriteUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val favoriteUseCase: FavoriteUseCase
) : BaseViewModel() {

    private val _onSuccessFavorites: MutableLiveData<List<FavoriteViewParams>> = MutableLiveData()
    val onSuccessFavorites: LiveData<List<FavoriteViewParams>> = _onSuccessFavorites

    private val _onSuccessDelete: MutableLiveData<Boolean> = MutableLiveData()
    val onSuccessDelete: LiveData<Boolean> = _onSuccessDelete

    fun callFavorites() = viewModelScope.launch {
        favoriteUseCase.getFavorites().let { favoriteViewParams ->
            _onSuccessFavorites.postValue(favoriteViewParams)
        }
    }

    fun deleteFavorite(movieId: Int) = viewModelScope.launch {
        with(favoriteUseCase) {
            getFavoriteWithCasts(movieId)?.let { favoriteAndCasts ->
                deleteFavoriteDetails(favoriteAndCasts.favorite.movieId)
                deleteFavoriteCasts(favoriteAndCasts.castList)
            }
            getFavoriteWithSimilars(movieId)?.let { favoriteAndSimilars ->
                deleteFavoriteSimilars(favoriteAndSimilars.similarList)
            }

            _onSuccessDelete.postValue(true)
        }
    }
}