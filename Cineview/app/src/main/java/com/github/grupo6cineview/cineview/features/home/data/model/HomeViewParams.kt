package com.github.grupo6cineview.cineview.features.home.data.model

import androidx.recyclerview.widget.DiffUtil
import com.github.grupo6cineview.cineview.db.entity.*

data class HomeViewParams(
    val id: Int,
    var posterPath: String,
    var backdropPath: String,
    val overview: String,
    val title: String,
    val voteCount: String,
    val voteAverage: String
) {

    fun toCarouselEntity() =
        CarouselEntity(
            movieId = id,
            posterPath = posterPath,
            backdropPath = backdropPath,
            overview = overview,
            title = title,
            voteCount = voteCount,
            voteAverage = voteAverage
        )

    fun toNowPlayingEntity() =
        NowPlayingEntity(
            movieId = id,
            posterPath = posterPath,
            backdropPath = backdropPath,
            overview = overview,
            title = title,
            voteCount = voteCount,
            voteAverage = voteAverage
        )

    fun toTopRatedEntity() =
        TopRatedEntity(
            movieId = id,
            posterPath = posterPath,
            backdropPath = backdropPath,
            overview = overview,
            title = title,
            voteCount = voteCount,
            voteAverage = voteAverage
        )

    fun toPopularEntity() =
        PopularEntity(
            movieId = id,
            posterPath = posterPath,
            backdropPath = backdropPath,
            overview = overview,
            title = title,
            voteCount = voteCount,
            voteAverage = voteAverage
        )

    fun toTrendingEntity() =
        TrendingEntity(
            movieId = id,
            posterPath = posterPath,
            backdropPath = backdropPath,
            overview = overview,
            title = title,
            voteCount = voteCount,
            voteAverage = voteAverage
        )

    companion object {

        val HOME_DIFF = object : DiffUtil.ItemCallback<HomeViewParams>() {

            override fun areItemsTheSame(
                oldItem: HomeViewParams,
                newItem: HomeViewParams
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: HomeViewParams,
                newItem: HomeViewParams
            ): Boolean = oldItem.id == newItem.id
        }
    }
}

fun List<HomeViewParams>.toCarouselEntityList(): List<CarouselEntity> {
    val list = mutableListOf<CarouselEntity>()

    this.forEach { item ->
        list.add(
            item.toCarouselEntity()
        )
    }

    return list
}

fun List<HomeViewParams>.toNowPlayingEntityList(): List<NowPlayingEntity> {
    val list = mutableListOf<NowPlayingEntity>()

    this.forEach { item ->
        list.add(
            item.toNowPlayingEntity()
        )
    }

    return list
}

fun List<HomeViewParams>.toTopRatedEntityList(): List<TopRatedEntity> {
    val list = mutableListOf<TopRatedEntity>()

    this.forEach { item ->
        list.add(
            item.toTopRatedEntity()
        )
    }

    return list
}

fun List<HomeViewParams>.toPopularEntityList(): List<PopularEntity> {
    val list = mutableListOf<PopularEntity>()

    this.forEach { item ->
        list.add(
            item.toPopularEntity()
        )
    }

    return list
}

fun List<HomeViewParams>.toTrendingEntityList(): List<TrendingEntity> {
    val list = mutableListOf<TrendingEntity>()

    this.forEach { item ->
        list.add(
            item.toTrendingEntity()
        )
    }

    return list
}