package com.github.grupo6cineview.cineview.db.entity.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cast_table")
data class CastEntity(
    @PrimaryKey
    @ColumnInfo(name = "cast_id")
    val castId: Int,
    @ColumnInfo(name = "original_name")
    val originalName: String,
    @ColumnInfo(name = "profile_path")
    val profilePath: String,
    val character: String,
    @ColumnInfo(name = "movie_related_id")
    val movieRelatedId: Int
)