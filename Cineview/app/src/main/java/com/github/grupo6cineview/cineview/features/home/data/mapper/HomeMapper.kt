package com.github.grupo6cineview.cineview.features.home.data.mapper

import com.github.grupo6cineview.cineview.extension.rateFormat
import com.github.grupo6cineview.cineview.extension.viewsFormat
import com.github.grupo6cineview.cineview.utils.ResponseApi
import com.github.grupo6cineview.cineview.extension.getFullImageUrl
import com.github.grupo6cineview.cineview.features.home.data.model.HomeResponse
import com.github.grupo6cineview.cineview.features.home.data.model.HomeViewParams

class HomeMapper {

    fun filterMoviesToHome(response: ResponseApi): List<HomeViewParams>? =
        when (response) {
            is ResponseApi.Success -> {
                (response.data as HomeResponse).let { homeResponse ->
                    val newList = mutableListOf<HomeViewParams>()

                    homeResponse.results
                        .filter { homeResult ->
                            with(homeResult) {
                                posterPath != null &&
                                        backdropPath != null &&
                                        overview != ""
                            }
                        }
                        .map { homeResult ->
                            homeResult.apply {
                                posterPath = posterPath?.getFullImageUrl(200)
                                backdropPath = backdropPath?.getFullImageUrl(500)
                            }
                        }
                        .forEach { homeResult ->
                            with(homeResult) {
                                newList.add(
                                    HomeViewParams(
                                        id = id,
                                        posterPath = posterPath ?: "",
                                        backdropPath = backdropPath ?: "",
                                        overview = overview,
                                        title = title,
                                        voteCount = voteCount.viewsFormat(),
                                        voteAverage = voteAverage.rateFormat()
                                    )
                                )
                            }
                        }

                    newList
                }
            }

            is ResponseApi.Error -> null
        }
}