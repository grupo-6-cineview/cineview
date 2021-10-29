package com.github.grupo6cineview.cineview.db.entity.favorite

import androidx.room.Embedded
import androidx.room.Relation

data class FavoriteWithSimilar(
    @Embedded val favorite: FavoriteEntity,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "movie_related_id"
    )
    val similarList: List<SimilarEntity>
)