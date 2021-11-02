package com.github.grupo6cineview.cineview.features.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.grupo6cineview.cineview.base.BaseViewModel
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Paging.FIRST_PAGE
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Paging.MAX_SIZE
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Paging.PAGE_SIZE
import com.github.grupo6cineview.cineview.features.home.data.model.HomeViewParams
import com.github.grupo6cineview.cineview.features.home.data.paging.HomePagingSource
import com.github.grupo6cineview.cineview.features.home.domain.HomeIntent
import com.github.grupo6cineview.cineview.features.home.domain.HomeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {

    private val _onSuccessCarousel: MutableLiveData<List<HomeViewParams>> = MutableLiveData()
    val onSuccessCarousel: LiveData<List<HomeViewParams>> = _onSuccessCarousel

    private val _onSuccessNowPlaying: MutableLiveData<List<HomeViewParams>> = MutableLiveData()
    val onSuccessNowPlaying: LiveData<List<HomeViewParams>> = _onSuccessNowPlaying

    private val _onSuccessTopRated: MutableLiveData<List<HomeViewParams>> = MutableLiveData()
    val onSuccessTopRated: LiveData<List<HomeViewParams>> = _onSuccessTopRated

    private val _onSuccessPopular: MutableLiveData<List<HomeViewParams>> = MutableLiveData()
    val onSuccessPopular: LiveData<List<HomeViewParams>> = _onSuccessPopular

    private val _onSuccessTrending: MutableLiveData<List<HomeViewParams>> = MutableLiveData()
    val onSuccessTrending: LiveData<List<HomeViewParams>> = _onSuccessTrending

    fun getNowPlayingMovies() =
        viewModelScope.launch {
            callApi(
                call = { homeUseCase.getNowPlayingMovies(FIRST_PAGE) },
                onSuccess = { data ->
                    (data as List<*>).filterIsInstance(HomeViewParams::class.java).let { listResult ->
                        _onSuccessCarousel.postValue(listResult)
                    }
                }
            )
        }

    fun getMovies(intent: HomeIntent) : Flow<PagingData<HomeViewParams>> =
        Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                maxSize = MAX_SIZE
            )
        ) {
            HomePagingSource(
                homeUseCase,
                intent
            )
        }.flow.cachedIn(viewModelScope)

    fun getMoviesFromDatabase(intent: HomeIntent) =
        viewModelScope.launch {
            homeUseCase.getMoviesFromDatabase(intent).let { homeViewParams ->
                when (intent) {
                    HomeIntent.Carousel -> _onSuccessCarousel.postValue(homeViewParams)

                    HomeIntent.NowPlaying -> _onSuccessNowPlaying.postValue(homeViewParams)

                    HomeIntent.Popular -> _onSuccessPopular.postValue(homeViewParams)

                    HomeIntent.TopRated -> _onSuccessTopRated.postValue(homeViewParams)

                    HomeIntent.Trending -> _onSuccessTrending.postValue(homeViewParams)
                }
            }
        }

    fun resetDatabase() =
        viewModelScope.launch {
            homeUseCase.resetDatabase()
        }
}