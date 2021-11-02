package com.github.grupo6cineview.cineview.features.home.data.repository

import com.github.grupo6cineview.cineview.api.ApiService
import com.github.grupo6cineview.cineview.base.BaseRepository
import com.github.grupo6cineview.cineview.db.dao.HomeDao
import com.github.grupo6cineview.cineview.db.entity.*
import com.github.grupo6cineview.cineview.utils.ResponseApi

class HomeRepository(
    private val homeDao: HomeDao
) : BaseRepository() {

    suspend fun getNowPlayingMovies(page: Int): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getNowPlayingMovies(page)
        }
    }

    suspend fun getPopularMovies(page: Int): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getPopularMovies(page)
        }
    }

    suspend fun getTopRatedMovies(page: Int): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getTopRatedMovies(page)
        }
    }

    suspend fun getTrendingMovies(page: Int): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getTrendingMovies(page = page)
        }
    }

    suspend fun insertCarouselMovies(movies: List<CarouselEntity>) = homeDao.insertCarouselMovies(movies)

    suspend fun insertNowPlayingMovies(movies: List<NowPlayingEntity>) = homeDao.insertNowPlayingMovies(movies)

    suspend fun insertTopRatedMovies(movies: List<TopRatedEntity>) = homeDao.insertTopRatedMovies(movies)

    suspend fun insertPopularMovies(movies: List<PopularEntity>) = homeDao.insertPopularMovies(movies)

    suspend fun insertTrendingMovies(movies: List<TrendingEntity>) = homeDao.insertTrendingMovies(movies)

    suspend fun getAllMoviesCarousel() = homeDao.getAllMoviesCarousel()

    suspend fun getAllMoviesNowPlaying() = homeDao.getAllMoviesNowPlaying()

    suspend fun getAllMoviesTopRated() = homeDao.getAllMoviesTopRated()

    suspend fun getAllMoviesPopular() = homeDao.getAllMoviesPopular()

    suspend fun getAllMoviesTrending() = homeDao.getAllMoviesTrending()

    suspend fun resetCarouselMovies() = homeDao.resetCarouselMovies()

    suspend fun resetNowPlayingMovies() = homeDao.resetNowPlayingMovies()

    suspend fun resetTopRatedMovies() = homeDao.resetTopRatedMovies()

    suspend fun resetPopularMovies() = homeDao.resetPopularMovies()

    suspend fun resetTrendingMovies() = homeDao.resetTrendingMovies()
}