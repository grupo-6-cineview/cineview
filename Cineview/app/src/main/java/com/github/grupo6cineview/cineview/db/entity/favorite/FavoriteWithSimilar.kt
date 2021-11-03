package com.github.grupo6cineview.cineview.db.entity.favorite

import androidx.room.Embedded
import androidx.room.Relation
import com.github.grupo6cineview.cineview.features.movie.data.model.similar.SimilarItem
import com.github.grupo6cineview.cineview.features.movie.data.model.viewparams.SimilarViewParams

data class FavoriteWithSimilar(
    @Embedded val favorite: FavoriteEntity,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "movie_related_id"
    )
    val similarList: List<SimilarEntity>
) {

    fun getSimilarViewParams(): SimilarViewParams {
        val list = mutableListOf<SimilarItem>()

        similarList.forEach { item ->
            list.add(
                item.toSimilarItem()
            )
        }

        return SimilarViewParams(similarMovies = list)
    }
}