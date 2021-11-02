package com.github.grupo6cineview.cineview.features.movie.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.grupo6cineview.cineview.base.BaseViewModel
import com.github.grupo6cineview.cineview.features.home.data.model.HomeViewParams
import com.github.grupo6cineview.cineview.features.movie.data.model.genre.GenresResponse
import com.github.grupo6cineview.cineview.features.movie.data.model.viewparams.CastViewParams
import com.github.grupo6cineview.cineview.features.movie.data.model.viewparams.DetailsViewParams
import com.github.grupo6cineview.cineview.features.movie.data.model.viewparams.SimilarViewParams
import com.github.grupo6cineview.cineview.features.movie.domain.MovieUseCase
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieUseCase: MovieUseCase
) : BaseViewModel() {

    private val _onSuccessGenres: MutableLiveData<GenresResponse> = MutableLiveData()
    val onSuccessGenres: LiveData<GenresResponse> get() = _onSuccessGenres

    private val _onSuccessDetails: MutableLiveData<DetailsViewParams> = MutableLiveData()
    val onSuccessDetails: LiveData<DetailsViewParams> get() = _onSuccessDetails

    private val _onSuccessCast: MutableLiveData<CastViewParams> = MutableLiveData()
    val onSuccessCast: LiveData<CastViewParams> get() = _onSuccessCast

    private val _onSuccessSimilar: MutableLiveData<SimilarViewParams> = MutableLiveData()
    val onSuccessSimilar: LiveData<SimilarViewParams> get() = _onSuccessSimilar

    private val _onVerifyFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val onVerifyFavorite: LiveData<Boolean> get() = _onVerifyFavorite

    private val _onSuccedLoadFromDatabase: MutableLiveData<HomeViewParams?> = MutableLiveData()
    val onSuccedLoadFromDatabase: LiveData<HomeViewParams?> get() = _onSuccedLoadFromDatabase

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
                onSuccess = { data -> _onSuccessDetails.postValue(data as? DetailsViewParams) }
            )
        }
    }

    fun getMovieCast(id: Int) {
        viewModelScope.launch {
            callApi(
                call = { movieUseCase.getMovieCast(id) },
                onSuccess = { data ->
                    _onSuccessCast.postValue(data as? CastViewParams)
                }
            )
        }
    }

    fun getSimilarMovies(id: Int) {
        viewModelScope.launch {
            callApi(
                call = { movieUseCase.getSimilarMovies(id) },
                onSuccess = { data ->
                    _onSuccessSimilar.postValue(data as? SimilarViewParams)
                }
            )
        }
    }

    fun verifyFavorite(movieId: Int) {
        viewModelScope.launch {
            movieUseCase.getFavoriteWithCasts(movieId)?.let {
                _onVerifyFavorite.postValue(true)
            } ?: _onVerifyFavorite.postValue(false)
        }
    }

    fun saveFavorite(
        movieDetails: DetailsViewParams,
        movieCast: CastViewParams,
        movieSimilar: SimilarViewParams
    ) {
        viewModelScope.launch {
            movieUseCase.saveFavoriteDetails(movieDetails)
            movieUseCase.saveFavoriteCasts(movieCast)
            movieUseCase.saveFavoriteSimilars(movieSimilar)
        }
    }

    fun getFavoriteWithCasts(movieId: Int) {
        viewModelScope.launch {
            movieUseCase.getFavoriteWithCasts(movieId)?.let { favWithCast ->
                _onSuccessDetails.value = favWithCast.favorite.toDetailsViewParams()
                _onSuccessCast.postValue(favWithCast.getCastViewParams())
            }
        }
    }

    fun getFavoriteWithSimilars(movieId: Int) {
        viewModelScope.launch {
            movieUseCase.getFavoriteWithSimilars(movieId)?.let { similarViewParams ->
                _onSuccessSimilar.postValue(similarViewParams)
            }
        }
    }

    fun deleteFavorite(
        movieDetails: DetailsViewParams,
        movieCast: CastViewParams,
        movieSimilar: SimilarViewParams
    ) {
        viewModelScope.launch {
            movieUseCase.deleteFavoriteDetails(movieDetails.movieId)
            movieUseCase.deleteFavoriteCasts(movieCast)
            movieUseCase.deleteFavoriteSimilars(movieSimilar)
        }
    }

    fun getMovieFromDatabase(
        movieId: Int,
        intent: String
    ) {
        viewModelScope.launch {
            movieUseCase.getMovieFromDatabase(
                movieId = movieId,
                intent = intent
            ).let { homeViewParams ->
                _onSuccedLoadFromDatabase.postValue(homeViewParams)
            }
        }
    }
}