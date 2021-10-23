package com.github.grupo6cineview.cineview.extension

import android.view.View
import android.view.View.*

fun View.setVisible(
    visible: Boolean,
    useInvisible: Boolean = false
) {
    visibility = when {
        visible -> VISIBLE
        else -> if (useInvisible) INVISIBLE else GONE
    }
}