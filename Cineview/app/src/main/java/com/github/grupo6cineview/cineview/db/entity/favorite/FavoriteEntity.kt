package com.github.grupo6cineview.cineview.db.entity.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.grupo6cineview.cineview.features.movie.data.model.details.ProductionCompany
import com.github.grupo6cineview.cineview.features.movie.data.model.genre.Genre

@Entity(tableName = "favorite_movies")
data class FavoriteEntity(
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String,
    val budget: Int,
    val genres: String,
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "original_title")
    val originalTitle: String,
    val overview: String,
    @ColumnInfo(name = "production_companies")
    val productionCompanies: String,
    @ColumnInfo(name = "release_date")
    var releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val title: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int
)