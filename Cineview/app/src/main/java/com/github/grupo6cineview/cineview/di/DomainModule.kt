package com.github.grupo6cineview.cineview.di

import com.github.grupo6cineview.cineview.features.account.domain.AccountUseCase
import com.github.grupo6cineview.cineview.features.favorite.domain.FavoriteUseCase
import com.github.grupo6cineview.cineview.features.home.domain.HomeUseCase
import com.github.grupo6cineview.cineview.features.movie.domain.MovieUseCase
import com.github.grupo6cineview.cineview.features.search.domain.SearchUseCase
import org.koin.dsl.module

object DomainModule {

    val useCaseModules = module {
        single {
            HomeUseCase(
                homeRepository = get(),
                homeMapper = get()
            )
        }
        single {
            SearchUseCase(
                searchRepository = get(),
                searchMapper = get()
            )
        }
        single {
            MovieUseCase(
                movieRepositoy = get(),
                movieMapper = get()
            )
        }
        single { FavoriteUseCase(favoriteRepository = get()) }
        single { AccountUseCase(accountRepository = get()) }
    }
}