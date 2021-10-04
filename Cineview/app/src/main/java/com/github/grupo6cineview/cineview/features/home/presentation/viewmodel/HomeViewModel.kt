package com.github.grupo6cineview.cineview.features.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.grupo6cineview.cineview.extensions.BaseViewModel
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Paging.FIRST_PAGE
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Paging.PAGE_SIZE
import com.github.grupo6cineview.cineview.features.home.data.model.HomeResult
import com.github.grupo6cineview.cineview.features.home.data.paging.HomePagingSource
import com.github.grupo6cineview.cineview.features.home.domain.HomeIntent
import com.github.grupo6cineview.cineview.features.home.domain.HomeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val homeUseCase = HomeUseCase()

    private val _onSuccessNowPlaying: MutableLiveData<List<HomeResult>> = MutableLiveData()
    val onSuccessNowPlaying: LiveData<List<HomeResult>> = _onSuccessNowPlaying

    fun getNowPlayingMovies() =
        viewModelScope.launch {
            callApi(
                call = { homeUseCase.getNowPlayingMovies(FIRST_PAGE) },
                onSuccess = { data ->
                    (data as List<*>).filterIsInstance(HomeResult::class.java).let { listResult ->
                        _onSuccessNowPlaying.postValue(listResult)
                    }
                }
            )
        }

    fun getMovies(intent: HomeIntent) : Flow<PagingData<HomeResult>> =
        Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            )
        ) {
            HomePagingSource(
                homeUseCase,
                intent
            )
        }.flow.cachedIn(viewModelScope)
}