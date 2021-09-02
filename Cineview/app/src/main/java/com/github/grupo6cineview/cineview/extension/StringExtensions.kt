package com.github.grupo6cineview.cineview.extensions

fun String.getFullImageUrl(width: Int) = "https://image.tmdb.org/t/p/w$width$this"