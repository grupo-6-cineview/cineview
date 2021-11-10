package com.github.grupo6cineview.cineview.extension

import android.content.res.Resources
import java.text.NumberFormat

fun Int.asDp() : Int =
    if (this == 0)
        0
    else
        (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun Int.viewsFormat() = "$this Views"

fun Int.runtimeFormat(): String {
    var hours = 0
    var minutes = this

    while (minutes - 60 > 0) {
        minutes -= 60
        hours++
    }

    return "${hours}h ${minutes}min"
}

fun Int.moneyFormat() =
    NumberFormat.getCurrencyInstance().let { format ->
        var string = "R"

        string += format.format(this.toDouble())

        string
    }