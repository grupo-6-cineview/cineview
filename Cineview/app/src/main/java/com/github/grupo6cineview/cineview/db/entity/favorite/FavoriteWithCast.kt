package com.github.grupo6cineview.cineview.db.entity.favorite

import androidx.room.Embedded
import androidx.room.Relation

data class FavoriteWithCast(
    @Embedded val favorite: FavoriteEntity,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "movie_related_id"
    )
    val castList: List<CastEntity>
)