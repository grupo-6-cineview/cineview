package com.github.grupo6cineview.cineview.features.movie.data.model.viewparams

import com.github.grupo6cineview.cineview.db.entity.favorite.SimilarEntity
import com.github.grupo6cineview.cineview.features.movie.data.model.similar.SimilarItem

data class SimilarViewParams(
    val similarMovies: List<SimilarItem>
) {

    fun toSimilarEntityList() : List<SimilarEntity> {
        val newList = mutableListOf<SimilarEntity>()

        similarMovies.forEach { item ->
            newList.add(
                item.toSimilarEntity()
            )
        }

        return newList
    }
}
