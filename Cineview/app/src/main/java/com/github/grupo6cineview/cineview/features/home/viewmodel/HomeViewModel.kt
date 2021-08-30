package com.github.grupo6cineview.cineview.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.grupo6cineview.cineview.extensions.BaseViewModel
import com.github.grupo6cineview.cineview.features.home.model.HomeUseCase
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val homeUseCase = HomeUseCase()

    private val _onSuccessNowPlaying: MutableLiveData<List<com.github.grupo6cineview.cineview.datamodel.Result>> =
        MutableLiveData()
    val onSuccessNowPlaying: LiveData<List<com.github.grupo6cineview.cineview.datamodel.Result>>
        get() = _onSuccessNowPlaying

    private val _onErrorNowPlaying: MutableLiveData<Int> =
        MutableLiveData()
    val onErrorNowPlaying: LiveData<Int>
        get() = _onErrorNowPlaying
    private val _onCustomErrorNowPlaying: MutableLiveData<Boolean> =
        MutableLiveData()
    val onCustomErrorNowPlaying: LiveData<Boolean>
        get() = _onCustomErrorNowPlaying


    fun getNowPlayingMovies() {
        viewModelScope.launch {
            callApi(
                { homeUseCase.getNowPlayingMovies() },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccessNowPlaying.postValue(
                        result?.filterIsInstance<com.github.grupo6cineview.cineview.datamodel.Result>()
                    )
                },
                onError = {
                    _onCustomErrorNowPlaying.postValue(true)
                }
            )
        }
    }

}