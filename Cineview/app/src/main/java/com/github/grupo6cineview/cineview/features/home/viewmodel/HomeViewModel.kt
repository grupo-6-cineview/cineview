package com.github.grupo6cineview.cineview.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.grupo6cineview.cineview.datamodel.SearchTrendingResult
import com.github.grupo6cineview.cineview.extensions.BaseViewModel
import com.github.grupo6cineview.cineview.extensions.ConstantsApp
import com.github.grupo6cineview.cineview.extensions.ResponseApi
import com.github.grupo6cineview.cineview.features.home.model.HomeUseCase
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val homeUseCase = HomeUseCase()

    private val _onSuccessNowPlaying: MutableLiveData<List<com.github.grupo6cineview.cineview.datamodel.NowPlayngResult>> =
        MutableLiveData()
    val onSuccessNowPlaying: LiveData<List<com.github.grupo6cineview.cineview.datamodel.NowPlayngResult>>
        get() = _onSuccessNowPlaying

    private val _onErrorNowPlaying: MutableLiveData<Int> =
        MutableLiveData()
    val onErrorNowPlaying: LiveData<Int>
        get() = _onErrorNowPlaying
    private val _onCustomErrorNowPlaying: MutableLiveData<Boolean> =
        MutableLiveData()
    val onCustomErrorNowPlaying: LiveData<Boolean>
        get() = _onCustomErrorNowPlaying

    private val _onSuccessTMDay = MutableLiveData<List<SearchTrendingResult>>()
    val onSuccessTMDay: LiveData<List<SearchTrendingResult>> get() = _onSuccessTMDay

    private val _onSuccessTMWeek = MutableLiveData<List<SearchTrendingResult>>()
    val onSuccessTMWeek: LiveData<List<SearchTrendingResult>> get() = _onSuccessTMWeek

    private val _onSuccessTTDay = MutableLiveData<List<SearchTrendingResult>>()
    val onSuccessTTDay: LiveData<List<SearchTrendingResult>> get() = _onSuccessTTDay

    private val _onSuccessTTWeek = MutableLiveData<List<SearchTrendingResult>>()
    val onSuccessTTWeek: LiveData<List<SearchTrendingResult>> get() = _onSuccessTTWeek

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            callApi(
                { homeUseCase.getNowPlayingMovies() },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccessNowPlaying.postValue(
                        result?.filterIsInstance<com.github.grupo6cineview.cineview.datamodel.NowPlayngResult>()
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
            homeUseCase.getTrendingMovies(mediaType, timeWindow).let { respose ->
                when (respose) {
                    is ResponseApi.Success -> {
                        val resultList = respose.data as List<*>

                        when (mediaType) {
                            ConstantsApp.Home.PATH_TRENDING_MOVIE -> {
                                when (timeWindow) {
                                    ConstantsApp.Home.PATH_TRENDING_DAY -> _onSuccessTMDay.postValue(resultList.filterIsInstance(SearchTrendingResult::class.java))
                                    ConstantsApp.Home.PATH_TRENDING_WEEK -> _onSuccessTMWeek.postValue(resultList.filterIsInstance(SearchTrendingResult::class.java))
                                }
                            }

                            ConstantsApp.Home.PATH_TRENDING_TV -> {
                                when (timeWindow) {
                                    ConstantsApp.Home.PATH_TRENDING_DAY -> _onSuccessTTDay.postValue(resultList.filterIsInstance(SearchTrendingResult::class.java))
                                    ConstantsApp.Home.PATH_TRENDING_WEEK -> _onSuccessTTWeek.postValue(resultList.filterIsInstance(SearchTrendingResult::class.java))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}