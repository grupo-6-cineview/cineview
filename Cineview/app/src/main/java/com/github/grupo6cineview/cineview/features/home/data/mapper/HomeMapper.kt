package com.github.grupo6cineview.cineview.features.home.data.mapper

import com.github.grupo6cineview.cineview.extensions.ResponseApi
import com.github.grupo6cineview.cineview.extensions.getFullImageUrl
import com.github.grupo6cineview.cineview.features.home.data.model.HomeResponse
import com.github.grupo6cineview.cineview.features.home.data.model.HomeResult

class HomeMapper {

    fun filterMovies(response: ResponseApi): List<HomeResult>? =
        when (response) {
            is ResponseApi.Success -> {
                (response.data as HomeResponse).let { homeResponse ->
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
                }
            }

            is ResponseApi.Error -> null
        }
}