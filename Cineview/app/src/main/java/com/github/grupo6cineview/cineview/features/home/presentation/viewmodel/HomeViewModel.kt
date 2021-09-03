package com.github.grupo6cineview.cineview.features.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.grupo6cineview.cineview.features.home.data.model.TrendingResult
import com.github.grupo6cineview.cineview.extensions.BaseViewModel
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Api.PATH_TRENDING_DAY
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Api.PATH_TRENDING_MOVIE
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Api.PATH_TRENDING_TV
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Api.PATH_TRENDING_WEEK
import com.github.grupo6cineview.cineview.features.home.data.model.NowPlayngResult
import com.github.grupo6cineview.cineview.features.home.domain.HomeUseCase
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val homeUseCase = HomeUseCase()

    private val _onSuccessNowPlaying: MutableLiveData<List<NowPlayngResult>> =
        MutableLiveData()
    val onSuccessNowPlaying: LiveData<List<NowPlayngResult>>
        get() = _onSuccessNowPlaying

    private val _onErrorNowPlaying: MutableLiveData<Int> =
        MutableLiveData()
    val onErrorNowPlaying: LiveData<Int>
        get() = _onErrorNowPlaying
    private val _onCustomErrorNowPlaying: MutableLiveData<Boolean> =
        MutableLiveData()
    val onCustomErrorNowPlaying: LiveData<Boolean>
        get() = _onCustomErrorNowPlaying

    private val _onSuccessTMDay = MutableLiveData<List<TrendingResult>>()
    val onSuccessTMDay: LiveData<List<TrendingResult>> get() = _onSuccessTMDay

    private val _onSuccessTMWeek = MutableLiveData<List<TrendingResult>>()
    val onSuccessTMWeek: LiveData<List<TrendingResult>> get() = _onSuccessTMWeek

    private val _onSuccessTTDay = MutableLiveData<List<TrendingResult>>()
    val onSuccessTTDay: LiveData<List<TrendingResult>> get() = _onSuccessTTDay

    private val _onSuccessTTWeek = MutableLiveData<List<TrendingResult>>()
    val onSuccessTTWeek: LiveData<List<TrendingResult>> get() = _onSuccessTTWeek

    private val _customCommand = MutableLiveData<Boolean>()
    val customCommand: LiveData<Boolean> get() = _customCommand

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            callApi(
                { homeUseCase.getNowPlayingMovies() },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccessNowPlaying.postValue(
                        result?.filterIsInstance<NowPlayngResult>()
                    )
                },
                onError = {
                    _onCustomErrorNowPlaying.postValue(true)
                }
            )
        }
    }

    fun getTrendingMovies(mediaType: String, timeWindow: String) {
        viewModelScope.launch {
            callApi(
                call = { homeUseCase.getTrendingMovies(mediaType, timeWindow) },
                onSuccess = { data ->
                    _customCommand.postValue(false)

                    val resultList = data as List<*>

                    when (mediaType) {
                        PATH_TRENDING_MOVIE -> {
                            when (timeWindow) {
                                PATH_TRENDING_DAY -> _onSuccessTMDay.postValue(resultList.filterIsInstance(
                                    TrendingResult::class.java))
                                PATH_TRENDING_WEEK -> _onSuccessTMWeek.postValue(resultList.filterIsInstance(
                                    TrendingResult::class.java))
                            }
                        }

                        PATH_TRENDING_TV -> {
                            when (timeWindow) {
                                PATH_TRENDING_DAY -> _onSuccessTTDay.postValue(resultList.filterIsInstance(
                                    TrendingResult::class.java))
                                PATH_TRENDING_WEEK -> {
                                    _onSuccessTTWeek.postValue(resultList.filterIsInstance(
                                        TrendingResult::class.java))

                                    _customCommand.postValue(false)
                                }
                            }
                        }
                    }
                }
            )
        }
    }

}