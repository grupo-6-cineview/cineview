package com.github.grupo6cineview.cineview.di

import com.github.grupo6cineview.cineview.features.home.data.mapper.HomeMapper
import com.github.grupo6cineview.cineview.features.home.data.repository.HomeRepository
import com.github.grupo6cineview.cineview.features.movie.data.mapper.MovieMapper
import com.github.grupo6cineview.cineview.features.movie.data.repository.MovieRepository
import com.github.grupo6cineview.cineview.features.search.data.mapper.SearchMapper
import com.github.grupo6cineview.cineview.features.search.data.repository.SearchRepository
import org.koin.dsl.module

object DataModule {

    val repositoryModules = module {
        single { HomeRepository() }
        single { SearchRepository() }
        single { MovieRepository() }
    }

    val mapperModules = module {
        single { HomeMapper() }
        single { SearchMapper() }
        single { MovieMapper() }
    }
}