package com.github.grupo6cineview.cineview.features.movie.domain

import com.github.grupo6cineview.cineview.utils.ResponseApi
import com.github.grupo6cineview.cineview.features.movie.data.mapper.MovieMapper
import com.github.grupo6cineview.cineview.features.movie.data.repository.MovieRepository

class MovieUseCase(
    private val movieRepositoy: MovieRepository,
    private val movieMapper: MovieMapper
) {

    suspend fun getAllGenres(): ResponseApi = movieRepositoy.getAllGenres()

    suspend fun getMovieDetails(id: Int): ResponseApi = movieMapper.filterMovieDetails(movieRepositoy.getMovieDetails(id))

    suspend fun getMovieCast(id: Int): ResponseApi = movieMapper.filterCastList(movieRepositoy.getMovieCast(id))

    suspend fun getSimilarMovies(id: Int): ResponseApi = movieMapper.filterSimilarMovies(movieRepositoy.getSimilarMovies(id))
}