package com.github.grupo6cineview.cineview.features.movie.movie.domain

import com.github.grupo6cineview.cineview.extensions.ResponseApi
import com.github.grupo6cineview.cineview.extensions.getFullImageUrl
import com.github.grupo6cineview.cineview.features.movie.movie.data.model.movie.MovieDetails
import com.github.grupo6cineview.cineview.features.movie.movie.data.model.tv.TvDetails
import com.github.grupo6cineview.cineview.features.movie.movie.data.repository.MovieRepository

class MovieUseCase {

    private val movieRepositoy = MovieRepository()

    suspend fun getMovieDetails(id: Int) : ResponseApi =
        movieRepositoy.getMovieDetails(id).let { response ->
            when (response) {
                is ResponseApi.Success -> {
                    (response.data as MovieDetails).let { movieDetails ->
                        movieDetails.apply {
                            backdrop_path = backdrop_path?.getFullImageUrl(500)
                            overview = overview ?: "Resumo Não Disponível no Momento."
                        }

                        ResponseApi.Success(movieDetails)
                    }
                }

                is ResponseApi.Error -> response
            }
        }

    suspend fun getTvDetails(id: Int) : ResponseApi =
        movieRepositoy.getTvDetails(id).let { response ->
            when (response) {
                is ResponseApi.Success -> {
                    (response.data as TvDetails).let { tvDetails ->
                        tvDetails.apply {
                            backdrop_path = backdrop_path?.getFullImageUrl(500)
                        }

                        ResponseApi.Success(tvDetails)
                    }
                }

                is ResponseApi.Error -> response
            }
        }

}