package com.github.grupo6cineview.cineview.extension

fun String.getFullImageUrl(width: Int) = "https://image.tmdb.org/t/p/w$width$this"

fun String.toDateBr() =
    split('-').let { list ->
        var newDate = ""

        list.asReversed().forEach { date ->
            newDate += date

            if (date != list.asReversed().last()) newDate += "/"
        }

        newDate
    }

fun String.getYearFromDate(): String =
    split('-').let { list ->
        list[0]
    }