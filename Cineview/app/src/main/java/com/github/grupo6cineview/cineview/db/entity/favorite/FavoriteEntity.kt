package com.github.grupo6cineview.cineview.db.entity.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.grupo6cineview.cineview.features.favorite.data.model.FavoriteViewParams
import com.github.grupo6cineview.cineview.features.movie.data.mapper.MovieMapper
import com.github.grupo6cineview.cineview.features.movie.data.model.details.DetailsItem
import com.github.grupo6cineview.cineview.features.movie.data.model.viewparams.DetailsViewParams

@Entity(tableName = "favorite_movies")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "room_id")
    val roomId: Long = 0,
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String,
    @ColumnInfo(name = "poster_path")
    var posterPath: String,
    val budget: String,
    val genres: String,
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "original_title")
    val originalTitle: String,
    val overview: String,
    @ColumnInfo(name = "production_companies")
    val productionCompanies: String,
    @ColumnInfo(name = "release_date")
    var releaseDate: String,
    val revenue: String,
    val runtime: String,
    val title: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: String,
    @ColumnInfo(name = "vote_count")
    val voteCount: String
) {

    fun toDetailsViewParams() =
        DetailsViewParams(
            movieId = movieId,
            backdrop = backdropPath,
            poster = posterPath,
            title = title,
            overview = overview,
            voteAverage = voteAverage,
            voteCount = voteCount,
            detailsList = getListDetailsItem()
        )

    private fun getListDetailsItem(): List<DetailsItem> {
        var titleItem = ""
        var subtitleItem = ""
        val list = mutableListOf<DetailsItem>()

        MovieMapper.moreInfoList.forEachIndexed { index, string ->
            when (index) {
                0 -> {
                    titleItem = string
                    subtitleItem = originalTitle
                }

                1 -> {
                    titleItem = string
                    subtitleItem = releaseDate
                }

                2 -> {
                    titleItem = string
                    subtitleItem = genres
                }

                3 -> {
                    titleItem = string
                    subtitleItem = runtime
                }

                4 -> {
                    titleItem = string
                    subtitleItem = budget
                }

                5 -> {
                    titleItem = string
                    subtitleItem = revenue
                }

                6 -> {
                    titleItem = string
                    subtitleItem = productionCompanies
                }
            }

            list.add(
                DetailsItem(
                    titleItem,
                    subtitleItem
                )
            )
        }

        return list
    }

    fun toFavoriteViewParams() =
        FavoriteViewParams(
            movieId = movieId,
            title = title,
            overview = overview,
            posterPath = posterPath,
            voteAverage = voteAverage.split(' ')[0]
        )
}

fun List<FavoriteEntity>.toFavoriteViewParamsList(): List<FavoriteViewParams> {
    val newList = mutableListOf<FavoriteViewParams>()

    this.forEach { item ->
        newList.add(
            item.toFavoriteViewParams()
        )
    }

    return newList
}