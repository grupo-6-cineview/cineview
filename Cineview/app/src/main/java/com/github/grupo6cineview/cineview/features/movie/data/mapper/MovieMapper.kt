package com.github.grupo6cineview.cineview.features.movie.data.mapper

import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.extension.moneyFormat
import com.github.grupo6cineview.cineview.extension.runtimeFormat
import com.github.grupo6cineview.cineview.extensions.ResponseApi
import com.github.grupo6cineview.cineview.extensions.getFullImageUrl
import com.github.grupo6cineview.cineview.extensions.getYearFromDate
import com.github.grupo6cineview.cineview.extensions.toDateBr
import com.github.grupo6cineview.cineview.features.movie.data.model.cast.CastItem
import com.github.grupo6cineview.cineview.features.movie.data.model.cast.CastResponse
import com.github.grupo6cineview.cineview.features.movie.data.model.details.DetailsItem
import com.github.grupo6cineview.cineview.features.movie.data.model.details.DetailsResponse
import com.github.grupo6cineview.cineview.features.movie.data.model.similar.SimilarItem
import com.github.grupo6cineview.cineview.features.movie.data.model.similar.SimilarResponse
import com.github.grupo6cineview.cineview.utils.GenresCache

class MovieMapper {

    private val moreInfoList = listOf(
        "Título Original",
        "Data de Lançamento",
        "Gêneros",
        "Duração",
        "Despesas",
        "Receita",
        "Produtoras"
    )

    fun filterMovieDetails(response: ResponseApi): ResponseApi =
        when (response) {
            is ResponseApi.Success -> {
                (response.data as? DetailsResponse)?.apply {
                    backdropPath = backdropPath?.getFullImageUrl(500)
                    releaseDate = releaseDate.toDateBr()

                    var title = ""
                    var subtitle = ""
                    val list = mutableListOf<DetailsItem>()

                    moreInfoList.forEachIndexed { index, string ->
                        when (index) {
                            0 -> {
                                title = string
                                subtitle = originalTitle
                            }

                            1 -> {
                                title = string
                                subtitle = releaseDate.toDateBr()
                            }

                            2 -> {
                                title = string
                                subtitle = ""

                                genres.forEach { genre ->
                                    subtitle += genre.name
                                    if (genre != genres.last()) subtitle += ", "
                                }
                            }

                            3 -> {
                                title = string
                                subtitle = runtime?.runtimeFormat() ?: "-"
                            }

                            4 -> {
                                title = string
                                subtitle = budget.moneyFormat()
                            }

                            5 -> {
                                title = string
                                subtitle = revenue.moneyFormat()
                            }

                            6 -> {
                                title = string
                                subtitle = ""

                                productionCompanies.forEach { company ->
                                    subtitle += company.name
                                    if (company != productionCompanies.last()) subtitle += ", "
                                }
                            }
                        }

                        list.add(
                            DetailsItem(
                                title,
                                subtitle
                            )
                        )
                    }

                    detailsList = list
                }?.let {  detailsResponse ->
                    ResponseApi.Success(detailsResponse)
                } ?: ResponseApi.Error(R.string.error_default)
            }

            is ResponseApi.Error -> response
        }

    fun filterCastList(response: ResponseApi): ResponseApi =
        when (response) {
            is ResponseApi.Success -> {
                (response.data as? CastResponse)?.cast?.let { castList ->
                    castList.filter { cast ->
                        cast.profilePath != null
                    }.let { filteredList ->
                        val newList = mutableListOf<CastItem>()

                        filteredList.forEach { castDetails ->
                            with(castDetails) {
                                newList.add(
                                    CastItem(
                                        poster = profilePath?.getFullImageUrl(200),
                                        name = originalName,
                                        character = character
                                    )
                                )
                            }
                        }

                        ResponseApi.Success(newList)
                    }
                } ?: ResponseApi.Error(R.string.error_default)
            }

            is ResponseApi.Error -> response
        }

    fun filterSimilarMovies(response: ResponseApi): ResponseApi =
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
                                        poster = posterPath?.getFullImageUrl(200),
                                        title = title,
                                        releaseYear = releaseDate.getYearFromDate(),
                                        genres = GenresCache.getGenresByMovie(genreIds)
                                    )
                                )
                            }
                        }

                        ResponseApi.Success(similarList)
                    }
                } ?: ResponseApi.Error(R.string.error_default)
            }

            is ResponseApi.Error -> response
        }
}