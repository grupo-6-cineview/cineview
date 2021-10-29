package com.github.grupo6cineview.cineview.db.entity.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "similar_movies")
data class SimilarEntity(
    @PrimaryKey
    @ColumnInfo(name = "similar_id")
    val similarId: Int,
    val poster: String,
    val title: String,
    @ColumnInfo(name = "release_year")
    val releaseYear: String,
    val genres: String,
    @ColumnInfo(name = "movie_related_id")
    val movieRelatedId: Int
)