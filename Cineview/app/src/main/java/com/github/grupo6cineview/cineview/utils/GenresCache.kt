package com.github.grupo6cineview.cineview.utils

import com.github.grupo6cineview.cineview.features.movie.data.model.genre.Genre

object GenresCache {
    private var genresCached: List<Genre>? = null
    val genresIsNull: Boolean get() = genresCached == null

    fun setGenresCached(listGenres: List<Genre>) { genresCached = listGenres }

    fun getGenresByMovie(ids: List<Int>): String {
        var genres = ""

        ids.forEachIndexed { i, id ->
            if (i < 3) {
                genresCached?.find { it.id == id }?.let { genre ->
                    genres += genre.name
                    if (i < 2 && id != ids.last()) genres += ", "
                } ?: kotlin.run { genres = "Não Informado" }
            }
        }

        return genres
    }
}