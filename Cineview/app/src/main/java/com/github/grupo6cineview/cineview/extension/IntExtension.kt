package com.github.grupo6cineview.cineview.extension

import android.content.res.Resources

fun Int.asDp() : Int =
    if (this == 0)
        0
    else
        (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()