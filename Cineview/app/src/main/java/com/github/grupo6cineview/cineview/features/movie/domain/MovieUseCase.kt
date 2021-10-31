package com.github.grupo6cineview.cineview.features.movie.domain

import com.github.grupo6cineview.cineview.features.home.data.model.HomeViewParams
import com.github.grupo6cineview.cineview.features.home.domain.HomeIntent
import com.github.grupo6cineview.cineview.features.movie.data.mapper.MovieMapper
import com.github.grupo6cineview.cineview.features.movie.data.model.viewparams.CastViewParams
import com.github.grupo6cineview.cineview.features.movie.data.model.viewparams.DetailsViewParams
import com.github.grupo6cineview.cineview.features.movie.data.model.viewparams.SimilarViewParams
import com.github.grupo6cineview.cineview.features.movie.data.repository.MovieRepository
import com.github.grupo6cineview.cineview.utils.ResponseApi

class MovieUseCase(
    private val movieRepositoy: MovieRepository,
    private val movieMapper: MovieMapper
) {

    suspend fun getAllGenres(): ResponseApi = movieRepositoy.getAllGenres()

    suspend fun getMovieDetails(id: Int): ResponseApi = movieMapper.filterMovieDetails(movieRepositoy.getMovieDetails(id))

    suspend fun getMovieCast(id: Int): ResponseApi = movieMapper.filterCastList(movieRepositoy.getMovieCast(id))

    suspend fun getSimilarMovies(id: Int): ResponseApi = movieMapper.filterSimilarMovies(
        response = movieRepositoy.getSimilarMovies(id),
        movieId = id
    )

    suspend fun saveFavoriteDetails(favorite: DetailsViewParams) = movieRepositoy.saveFavoriteDetails(favorite.toFavoriteEntity())

    suspend fun saveFavoriteCasts(casts: CastViewParams) = movieRepositoy.saveFavoriteCasts(casts.toCastEntityList())

    suspend fun saveFavoriteSimilars(similars: SimilarViewParams) = movieRepositoy.saveFavoriteSimilars(similars.toSimilarEntityList())

    suspend fun getFavoriteWithCasts(movieId: Int) = movieRepositoy.getFavoriteWithCasts(movieId)

    suspend fun getFavoriteWithSimilars(movieId: Int) = movieRepositoy.getFavoriteWithSimilars(movieId)?.getSimilarViewParams()

    suspend fun deleteFavoriteDetails(favorite: DetailsViewParams) = movieRepositoy.deleteFavoriteDetails(favorite.toFavoriteEntity())

    suspend fun deleteFavoriteCasts(casts: CastViewParams) = movieRepositoy.deleteFavoriteCasts(casts.toCastEntityList())

    suspend fun deleteFavoriteSimilars(similars: SimilarViewParams) = movieRepositoy.deleteFavoriteSimilars(similars.toSimilarEntityList())

    suspend fun getMovieFromDatabase(
        movieId: Int,
        intent: String
    ): HomeViewParams? =
        when (intent) {
            HomeIntent.Carousel.name -> movieRepositoy.getCarouselMovie(movieId)?.toHomeViewParams()

            HomeIntent.NowPlaying.name -> movieRepositoy.getNowPlayingMovie(movieId)?.toHomeViewParams()

            HomeIntent.Popular.name -> movieRepositoy.getPopularMovie(movieId)?.toHomeViewParams()

            HomeIntent.TopRated.name -> movieRepositoy.getTopRatedMovie(movieId)?.toHomeViewParams()

            HomeIntent.Trending.name -> movieRepositoy.getTrendingMovie(movieId)?.toHomeViewParams()

            else -> null
        }
}