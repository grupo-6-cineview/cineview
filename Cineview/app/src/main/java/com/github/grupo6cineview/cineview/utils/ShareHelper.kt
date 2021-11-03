package com.github.grupo6cineview.cineview.utils

import android.content.Context
import android.content.Intent

object ShareHelper {

    fun onClickShare(
        context: Context,
        msg: String
    ) {
        Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, msg)
            type = "text/plain"
        }.let { intent ->
            context.startActivity(
                Intent.createChooser(intent, null)
            )
        }
    }
}