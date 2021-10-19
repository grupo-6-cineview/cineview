package com.github.grupo6cineview.cineview.features.movie.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.grupo6cineview.cineview.extensions.BaseViewModel
import com.github.grupo6cineview.cineview.features.movie.data.model.cast.CastDetails
import com.github.grupo6cineview.cineview.features.movie.data.model.cast.CastItem
import com.github.grupo6cineview.cineview.features.movie.data.model.details.DetailsResponse
import com.github.grupo6cineview.cineview.features.movie.data.model.genre.GenresResponse
import com.github.grupo6cineview.cineview.features.movie.data.model.similar.SimilarItem
import com.github.grupo6cineview.cineview.features.movie.data.model.similar.SimilarResult
import kotlinx.coroutines.launch

class MovieViewModel : BaseViewModel() {

    private val movieUseCase =
        com.github.grupo6cineview.cineview.features.movie.domain.MovieUseCase()

    private val _onSuccessGenres: MutableLiveData<GenresResponse> = MutableLiveData()
    val onSuccessGenres: LiveData<GenresResponse> get() = _onSuccessGenres

    private val _onSuccessDetails: MutableLiveData<DetailsResponse> = MutableLiveData()
    val onSuccessDetails: LiveData<DetailsResponse> get() = _onSuccessDetails

    private val _onSuccessCast: MutableLiveData<List<CastItem>> = MutableLiveData()
    val onSuccessCast: LiveData<List<CastItem>> get() = _onSuccessCast

    private val _onSuccessSimilar: MutableLiveData<List<SimilarItem>> = MutableLiveData()
    val onSuccessSimilar: LiveData<List<SimilarItem>> get() = _onSuccessSimilar

    fun getAllGenres() {
        viewModelScope.launch {
            callApi(
                call = { movieUseCase.getAllGenres() },
                onSuccess = { data ->
                    _onSuccessGenres.postValue(data as? GenresResponse)
                }
            )
        }
    }

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            callApi(
                call = { movieUseCase.getMovieDetails(id) },
                onSuccess = { data -> _onSuccessDetails.postValue(data as? DetailsResponse) }
            )
        }
    }

    fun getMovieCast(id: Int) {
        viewModelScope.launch {
            callApi(
                call = { movieUseCase.getMovieCast(id) },
                onSuccess = { data ->
                    (data as? List<*>)?.let { list ->
                        _onSuccessCast.postValue(list.filterIsInstance<CastItem>())
                    }
                }
            )
        }
    }

    fun getSimilarMovies(id: Int) {
        viewModelScope.launch {
            callApi(
                call = { movieUseCase.getSimilarMovies(id) },
                onSuccess = { data ->
                    (data as? List<*>)?.let { list ->
                        _onSuccessSimilar.postValue(list.filterIsInstance<SimilarItem>())
                    }
                }
            )
        }
    }
}