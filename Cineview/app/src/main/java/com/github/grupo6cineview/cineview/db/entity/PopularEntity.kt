package com.github.grupo6cineview.cineview.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_movies")
data class PopularEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "porter_path")
    var posterPath: String,
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String,
    val overview: String,
    val title: String,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double
)