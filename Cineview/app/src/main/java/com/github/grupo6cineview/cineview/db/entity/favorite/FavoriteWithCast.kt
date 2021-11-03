package com.github.grupo6cineview.cineview.db.entity.favorite

import androidx.room.Embedded
import androidx.room.Relation
import com.github.grupo6cineview.cineview.features.movie.data.model.cast.CastItem
import com.github.grupo6cineview.cineview.features.movie.data.model.viewparams.CastViewParams

data class FavoriteWithCast(
    @Embedded val favorite: FavoriteEntity,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "movie_related_id"
    )
    val castList: List<CastEntity>
) {

    fun getCastViewParams(): CastViewParams {
        val list = mutableListOf<CastItem>()

        castList.forEach { item ->
            list.add(
                item.toCastItem()
            )
        }

        return CastViewParams(castItems = list)
    }
}