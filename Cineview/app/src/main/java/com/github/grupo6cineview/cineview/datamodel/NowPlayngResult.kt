package com.github.grupo6cineview.cineview.datamodel

import androidx.recyclerview.widget.DiffUtil

data class NowPlayngResult(
    val adult: Boolean?,
    var backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    var poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)