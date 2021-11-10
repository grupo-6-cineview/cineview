package com.github.grupo6cineview.cineview.features.movie.data.mapper

import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.db.entity.favorite.FavoriteEntity
import com.github.grupo6cineview.cineview.extension.*
import com.github.grupo6cineview.cineview.utils.ResponseApi
import com.github.grupo6cineview.cineview.features.movie.data.model.cast.CastItem
import com.github.grupo6cineview.cineview.features.movie.data.model.cast.CastResponse
import com.github.grupo6cineview.cineview.features.movie.data.model.details.DetailsItem
import com.github.grupo6cineview.cineview.features.movie.data.model.details.DetailsResponse
import com.github.grupo6cineview.cineview.features.movie.data.model.similar.SimilarItem
import com.github.grupo6cineview.cineview.features.movie.data.model.similar.SimilarResponse
import com.github.grupo6cineview.cineview.features.movie.data.model.viewparams.CastViewParams
import com.github.grupo6cineview.cineview.features.movie.data.model.viewparams.DetailsViewParams
import com.github.grupo6cineview.cineview.features.movie.data.model.viewparams.SimilarViewParams
import com.github.grupo6cineview.cineview.utils.GenresCache

class MovieMapper {

    companion object {
        val moreInfoList = listOf(
            "Título Original",
            "Data de Lançamento",
            "Gêneros",
            "Duração",
            "Despesas",
            "Receita",
            "Produtoras"
        )
    }

    fun filterMovieDetails(response: ResponseApi): ResponseApi =
        when (response) {
            is ResponseApi.Success -> {
                (response.data as? DetailsResponse)?.run {
                    var titleItem = ""
                    var subtitleItem = ""
                    val list = mutableListOf<DetailsItem>()

                    moreInfoList.forEachIndexed { index, string ->
                        when (index) {
                            0 -> {
                                titleItem = string
                                subtitleItem = originalTitle
                            }

                            1 -> {
                                titleItem = string
                                subtitleItem = releaseDate.toDateBr()
                            }

                            2 -> {
                                titleItem = string
                                subtitleItem = ""

                                genres.forEach { genre ->
                                    subtitleItem += genre.name
                                    if (genre != genres.last()) subtitleItem += ", "
                                }
                            }

                            3 -> {
                                titleItem = string
                                subtitleItem = runtime?.runtimeFormat() ?: "-"
                            }

                            4 -> {
                                titleItem = string
                                subtitleItem = budget.moneyFormat()
                            }

                            5 -> {
                                titleItem = string
                                subtitleItem = revenue.moneyFormat()
                            }

                            6 -> {
                                titleItem = string
                                subtitleItem = ""

                                productionCompanies.forEach { company ->
                                    subtitleItem += company.name
                                    if (company != productionCompanies.last()) subtitleItem += ", "
                                }
                            }
                        }

                        list.add(
                            DetailsItem(
                                titleItem,
                                subtitleItem
                            )
                        )
                    }

                    DetailsViewParams(
                        movieId = id,
                        backdrop = backdropPath?.getFullImageUrl(500) ?: "",
                        poster = posterPath?.getFullImageUrl(200) ?: "",
                        title = title,
                        overview = overview ?: "",
                        voteAverage = voteAverage.rateFormat(),
                        voteCount = voteCount.viewsFormat(),
                        detailsList = list
                    )
                }?.let {  detailsViewParams ->
                    ResponseApi.Success(detailsViewParams)
                } ?: ResponseApi.Error(R.string.error_default)
            }

            is ResponseApi.Error -> response
        }

    fun filterCastList(response: ResponseApi): ResponseApi =
        when (response) {
            is ResponseApi.Success -> {
                (response.data as? CastResponse)?.let { castResponse ->
                    castResponse.cast.let { castList ->
                        castList.filter { cast ->
                            cast.profilePath != null
                        }.let { filteredList ->
                            val newList = mutableListOf<CastItem>()

                            filteredList.forEach { castDetails ->
                                with(castDetails) {
                                    newList.add(
                                        CastItem(
                                            castId = id,
                                            poster = profilePath?.getFullImageUrl(200),
                                            name = originalName,
                                            character = character,
                                            movieRelatedId = castResponse.movieId
                                        )
                                    )
                                }
                            }

                            ResponseApi.Success(
                                CastViewParams(castItems = newList)
                            )
                        }
                    }
                } ?: ResponseApi.Error(R.string.error_default)
            }

            is ResponseApi.Error -> response
        }

    fun filterSimilarMovies(
        response: ResponseApi,
        movieId: Int
    ): ResponseApi =
        when (response) {
            is ResponseApi.Success -> {
                (response.data as? SimilarResponse)?.results?.let { results ->
                    results.filter { similarMovie ->
                        with(similarMovie) {
                            posterPath != null &&
                                    backdropPath != null &&
                                    overview != ""
                        }
                    }.let { resultList ->
                        val similarList = mutableListOf<SimilarItem>()

                        resultList.forEach { similarResult ->
                            with(similarResult) {
                                similarList.add(
                                    SimilarItem(
                                        similarId = id,
                                        poster = posterPath?.getFullImageUrl(200),
                                        title = title,
                                        releaseYear = releaseDate.getYearFromDate(),
                                        genres = GenresCache.getGenresByMovie(genreIds),
                                        movieRelatedId = movieId
                                    )
                                )
                            }
                        }

                        ResponseApi.Success(
                            SimilarViewParams(similarMovies = similarList)
                        )
                    }
                } ?: ResponseApi.Error(R.string.error_default)
            }

            is ResponseApi.Error -> response
        }
}