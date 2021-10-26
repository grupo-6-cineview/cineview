package com.github.grupo6cineview.cineview.di

import com.github.grupo6cineview.cineview.features.home.presentation.viewmodel.HomeViewModel
import com.github.grupo6cineview.cineview.features.movie.presentation.viewmodel.MovieViewModel
import com.github.grupo6cineview.cineview.features.search.presentation.viewmodel.SearchViewModel
import org.koin.dsl.module

object AppModule {

    val viewModelModules = module {
        single { HomeViewModel(homeUseCase = get()) }
        single { SearchViewModel(searchUseCase = get()) }
        single { MovieViewModel(movieUseCase = get()) }
    }
}