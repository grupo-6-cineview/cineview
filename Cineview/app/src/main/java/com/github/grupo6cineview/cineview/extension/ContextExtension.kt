package com.github.grupo6cineview.cineview.extension

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources

fun Context.getDrawable2(id: Int) : Drawable? = AppCompatResources.getDrawable(this, id)