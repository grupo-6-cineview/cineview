package com.github.grupo6cineview.cineview.features.favorite.domain

import com.github.grupo6cineview.cineview.db.entity.favorite.CastEntity
import com.github.grupo6cineview.cineview.db.entity.favorite.SimilarEntity
import com.github.grupo6cineview.cineview.db.entity.favorite.toFavoriteViewParamsList
import com.github.grupo6cineview.cineview.features.favorite.data.repository.FavoriteRepository

class FavoriteUseCase(
    private val favoriteRepository: FavoriteRepository
) {

    suspend fun getFavorites() = favoriteRepository.getFavorites().asReversed().toFavoriteViewParamsList()

    suspend fun getFavoriteWithCasts(movieId: Int) = favoriteRepository.getFavoriteWithCasts(movieId)

    suspend fun getFavoriteWithSimilars(movieId: Int) = favoriteRepository.getFavoriteWithSimilars(movieId)

    suspend fun deleteFavoriteDetails(favoriteId: Int) = favoriteRepository.deleteFavoriteDetails(favoriteId)

    suspend fun deleteFavoriteCasts(casts: List<CastEntity>) =
        casts.forEach { item ->
            favoriteRepository.deleteFavoriteCasts(item.castId)
        }

    suspend fun deleteFavoriteSimilars(similars: List<SimilarEntity>) =
        similars.forEach { item ->
            favoriteRepository.deleteFavoriteSimilars(item.similarId)
        }
}