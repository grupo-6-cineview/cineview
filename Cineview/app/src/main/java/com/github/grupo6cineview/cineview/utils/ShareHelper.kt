package com.github.grupo6cineview.cineview.utils

import android.content.Context
import android.content.Intent
import com.github.grupo6cineview.cineview.R

object ShareHelper {

    fun onClickShare(
        context: Context,
        movie: String
    ) {
        Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                context.getString(
                    R.string.share_message,
                    movie
                )
            )
            type = "text/plain"
        }.let { intent ->
            context.startActivity(
                Intent.createChooser(intent, null)
            )
        }
    }
}