package com.github.grupo6cineview.cineview.features.movie.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.grupo6cineview.cineview.extensions.BaseViewModel
import com.github.grupo6cineview.cineview.features.movie.data.model.movie.MovieDetails
import com.github.grupo6cineview.cineview.features.movie.data.model.tv.TvDetails
import com.github.grupo6cineview.cineview.features.movie.domain.MovieUseCase
import kotlinx.coroutines.launch

class MovieViewModel : BaseViewModel() {

    private val movieUseCase = MovieUseCase()

    private val _onSuccessMovieDetails = MutableLiveData<MovieDetails>()
    val onSuccessMovieDetails: LiveData<MovieDetails> get() = _onSuccessMovieDetails

    private val _onSuccessTvDetails = MutableLiveData<TvDetails>()
    val onSuccessTvDetails: LiveData<TvDetails> get() = _onSuccessTvDetails

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            callApi(
                call = { movieUseCase.getMovieDetails(id) },
                onSuccess = { data ->
                    _onSuccessMovieDetails.postValue(data as MovieDetails)
                },
                onError = {
                    Log.d("api", "ResponseAPi.Error in MovieViewModel, fun getMovieDetails")
                    print("")
                }
            )
        }
    }

    fun getTvDetails(id: Int) {
        viewModelScope.launch {
            callApi(
                call = { movieUseCase.getTvDetails(id) },
                onSuccess = { data ->
                    _onSuccessTvDetails.postValue(data as TvDetails)
                },
                onError = {
                    Log.d("api", "ResponseAPi.Error in MovieViewModel, fun getTvDetails")
                    print("")
                }
            )
        }
    }

}