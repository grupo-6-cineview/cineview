package com.github.grupo6cineview.cineview.features.home.domain

import com.github.grupo6cineview.cineview.db.entity.toHomeVIewParamsList
import com.github.grupo6cineview.cineview.utils.ResponseApi
import com.github.grupo6cineview.cineview.features.home.data.mapper.HomeMapper
import com.github.grupo6cineview.cineview.features.home.data.model.*
import com.github.grupo6cineview.cineview.features.home.data.repository.HomeRepository

class HomeUseCase(
    private val homeRepository: HomeRepository,
    private val homeMapper: HomeMapper
) {
    private var nowPlayingMoviesSize = 0
    private var topRatedMoviesSize = 0
    private var popularMoviesSize = 0
    private var trendingMoviesSize = 0

    suspend fun getNowPlayingMovies(page: Int): ResponseApi =
        homeRepository.getNowPlayingMovies(page).let { respone ->
            when (respone) {
                is ResponseApi.Success -> {
                    homeMapper.filterMoviesToHome(respone).let { listFiltered ->
                        listFiltered?.let { homeViewParams ->
                            homeRepository.insertCarouselMovies(homeViewParams.toCarouselEntityList())
                        }

                        ResponseApi.Success(listFiltered)
                    }
                }

                is ResponseApi.Error -> respone
            }
        }

    suspend fun getMovies(intent: HomeIntent, page: Int): List<HomeViewParams>? =
        when (intent) {
            HomeIntent.NowPlaying -> {
                with (homeRepository) {
                    getNowPlayingMovies(page).let { response ->
                        homeMapper.filterMoviesToHome(response)?.let { homeViewParams ->
                            if (nowPlayingMoviesSize < MAX_SIZE_DATABASE) {
                                insertNowPlayingMovies(homeViewParams.toNowPlayingEntityList())
                                nowPlayingMoviesSize += homeViewParams.size
                            }

                            homeViewParams
                        }
                    }
                }
            }

            HomeIntent.Popular -> {
                with (homeRepository) {
                    getPopularMovies(page).let { response ->
                        homeMapper.filterMoviesToHome(response)?.let { homeViewParams ->
                            if (popularMoviesSize < MAX_SIZE_DATABASE) {
                                insertPopularMovies(homeViewParams.toPopularEntityList())
                                popularMoviesSize += homeViewParams.size
                            }

                            homeViewParams
                        }
                    }
                }
            }

            HomeIntent.TopRated -> {
                with (homeRepository) {
                    getTopRatedMovies(page).let { response ->
                        homeMapper.filterMoviesToHome(response)?.let { homeViewParams ->
                            if (topRatedMoviesSize < MAX_SIZE_DATABASE) {
                                insertTopRatedMovies(homeViewParams.toTopRatedEntityList())
                                topRatedMoviesSize += homeViewParams.size
                            }

                            homeViewParams
                        }
                    }
                }
            }

            HomeIntent.Trending -> {
                with (homeRepository) {
                    getTrendingMovies(page).let { response ->
                        homeMapper.filterMoviesToHome(response)?.let { homeViewParams ->
                            if (trendingMoviesSize < MAX_SIZE_DATABASE) {
                                insertTrendingMovies(homeViewParams.toTrendingEntityList())
                                trendingMoviesSize += homeViewParams.size
                            }

                            homeViewParams
                        }
                    }
                }
            }

            else -> null
        }

    suspend fun getMoviesFromDatabase(intent: HomeIntent): List<HomeViewParams> =
        when (intent) {
            HomeIntent.Carousel -> homeRepository.getAllMoviesCarousel().toHomeVIewParamsList()

            HomeIntent.NowPlaying -> homeRepository.getAllMoviesNowPlaying().toHomeVIewParamsList()

            HomeIntent.Popular -> homeRepository.getAllMoviesPopular().toHomeVIewParamsList()

            HomeIntent.TopRated -> homeRepository.getAllMoviesTopRated().toHomeVIewParamsList()

            HomeIntent.Trending -> homeRepository.getAllMoviesTrending().toHomeVIewParamsList()
        }

    suspend fun resetDatabase() = with(homeRepository) {
        resetCarouselMovies()
        resetNowPlayingMovies()
        resetTopRatedMovies()
        resetPopularMovies()
        resetTrendingMovies()
    }

    companion object {
        private const val MAX_SIZE_DATABASE = 100
    }
}