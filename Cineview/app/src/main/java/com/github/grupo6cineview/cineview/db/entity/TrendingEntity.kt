package com.github.grupo6cineview.cineview.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.grupo6cineview.cineview.features.home.data.model.HomeViewParams

@Entity(tableName = "trending_movies")
data class TrendingEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "porter_path")
    var posterPath: String,
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String,
    val overview: String,
    val title: String,
    @ColumnInfo(name = "vote_count")
    val voteCount: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: String
) {

    fun toHomeViewParams() =
        HomeViewParams(
            id = id,
            posterPath = posterPath,
            backdropPath = backdropPath,
            overview = overview,
            title = title,
            voteCount = voteCount,
            voteAverage = voteAverage
        )
}