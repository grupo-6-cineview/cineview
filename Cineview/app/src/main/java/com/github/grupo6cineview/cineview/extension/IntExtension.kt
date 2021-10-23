package com.github.grupo6cineview.cineview.extension

import android.content.res.Resources
import java.text.NumberFormat

fun Int.asDp() : Int =
    if (this == 0)
        0
    else
        (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun Int.viewsFormat() = "$this Views"

fun Int.runtimeFormat() = "$this Minutos"

fun Int.moneyFormat() =
    NumberFormat.getCurrencyInstance().let { format ->
        var string = "R"

        string += format.format(this.toDouble())

        string
    }