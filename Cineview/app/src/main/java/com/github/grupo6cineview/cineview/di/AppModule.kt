package com.github.grupo6cineview.cineview.di

import com.github.grupo6cineview.cineview.features.home.presentation.viewmodel.HomeViewModel
import com.github.grupo6cineview.cineview.features.movie.presentation.viewmodel.MovieViewModel
import com.github.grupo6cineview.cineview.features.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val viewModelModules = module {
        viewModel { HomeViewModel(homeUseCase = get()) }
        viewModel { SearchViewModel(searchUseCase = get()) }
        viewModel { MovieViewModel(movieUseCase = get()) }
    }
}