package com.github.grupo6cineview.cineview.db.entity.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.grupo6cineview.cineview.features.movie.data.model.similar.SimilarItem

@Entity(tableName = "similar_movies")
data class SimilarEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "room_id")
    val roomId: Long = 0,
    @ColumnInfo(name = "similar_id")
    val similarId: Int,
    val poster: String,
    val title: String,
    @ColumnInfo(name = "release_year")
    val releaseYear: String,
    val genres: String,
    @ColumnInfo(name = "movie_related_id")
    val movieRelatedId: Int
) {

    fun toSimilarItem() =
        SimilarItem(
            similarId = similarId,
            poster = poster,
            title = title,
            releaseYear = releaseYear,
            genres = genres,
            movieRelatedId = movieRelatedId
        )
}