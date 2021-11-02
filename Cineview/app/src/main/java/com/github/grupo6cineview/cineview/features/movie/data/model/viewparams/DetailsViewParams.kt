package com.github.grupo6cineview.cineview.features.movie.data.model.viewparams

import com.github.grupo6cineview.cineview.db.entity.favorite.FavoriteEntity
import com.github.grupo6cineview.cineview.features.movie.data.model.details.DetailsItem

data class DetailsViewParams(
    val movieId: Int,
    val backdrop: String,
    val poster: String,
    val title: String,
    val overview: String,
    val voteAverage: String,
    val voteCount: String,
    val detailsList: List<DetailsItem>
) {
    fun toFavoriteEntity() =
        FavoriteEntity(
            backdropPath = backdrop,
            posterPath = poster,
            budget = detailsList[4].subtitle,
            genres = detailsList[2].subtitle,
            movieId = movieId,
            originalTitle = detailsList[0].subtitle,
            overview = overview,
            productionCompanies = detailsList[6].subtitle,
            releaseDate = detailsList[1].subtitle,
            revenue = detailsList[3].subtitle,
            runtime = detailsList[5].subtitle,
            title = title,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
}