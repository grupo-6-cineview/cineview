package com.github.grupo6cineview.cineview.features.home.domain

import com.github.grupo6cineview.cineview.extensions.ResponseApi
import com.github.grupo6cineview.cineview.features.home.data.mapper.HomeMapper
import com.github.grupo6cineview.cineview.features.home.data.model.HomeResult
import com.github.grupo6cineview.cineview.features.home.data.repository.HomeRepository

class HomeUseCase {

    private val homeRepository = HomeRepository()
    private val homeMapper = HomeMapper()

    suspend fun getNowPlayingMovies(page: Int): ResponseApi =
        homeRepository.getNowPlayingMovies(page).let { respone ->
            when (respone) {
                is ResponseApi.Success -> {
                    homeMapper.filterMoviesToHome(respone).let { listFiltered ->
                        ResponseApi.Success(listFiltered)
                    }
                }

                is ResponseApi.Error -> respone
            }
        }

    suspend fun getMovies(intent: HomeIntent, page: Int): List<HomeResult>? =
        when (intent) {
            HomeIntent.NowPlaying -> {
                homeRepository.getNowPlayingMovies(page).let { response ->
                    homeMapper.filterMoviesToHome(response)
                }
            }

            HomeIntent.Popular -> {
                homeRepository.getPopularMovies(page).let { response ->
                    homeMapper.filterMoviesToHome(response)
                }
            }

            HomeIntent.TopRated -> {
                homeRepository.getTopRatedMovies(page).let { response ->
                    homeMapper.filterMoviesToHome(response)
                }
            }

            HomeIntent.Trending -> {
                homeRepository.getTrendingMovies(page).let { response ->
                    homeMapper.filterMoviesToHome(response)
                }
            }
        }
}