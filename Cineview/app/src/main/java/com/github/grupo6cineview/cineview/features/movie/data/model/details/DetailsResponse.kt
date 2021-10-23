package com.github.grupo6cineview.cineview.features.movie.data.model.details

import com.github.grupo6cineview.cineview.features.movie.data.model.genre.Genre
import com.google.gson.annotations.SerializedName

data class DetailsResponse(
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    val budget: Int,
    val genres: List<Genre>,
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("release_date")
    var releaseDate: String,
    val revenue: Int,
    val runtime: Int?,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    var detailsList: List<DetailsItem> = listOf()
)