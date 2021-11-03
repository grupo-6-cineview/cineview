package com.github.grupo6cineview.cineview.features.movie.data.model.viewparams

import com.github.grupo6cineview.cineview.db.entity.favorite.CastEntity
import com.github.grupo6cineview.cineview.features.movie.data.model.cast.CastItem

data class CastViewParams(
    val castItems: List<CastItem>
) {

    fun toCastEntityList(): List<CastEntity> {
        val list = mutableListOf<CastEntity>()

        castItems.forEach { item ->
            list.add(
                item.toCastEntity()
            )
        }

        return list
    }
}