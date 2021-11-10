package com.github.grupo6cineview.cineview.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.content.edit
import com.github.grupo6cineview.cineview.R

object ShareHelper {

    private const val SHARED_PREFERENCE = "SHARED_PREFERENCE"
    private const val SHARE_COUNT = "SHARE_COUNT"

    private fun incrementShareCount(context: Context) =
        context.getSharedPreferences(SHARED_PREFERENCE, Activity.MODE_PRIVATE).run {
            edit {
                putInt(
                    SHARE_COUNT,
                    getInt(SHARE_COUNT, 0) + 1
                )
            }
        }

    fun getShareCount(context: Context) =
        context.getSharedPreferences(SHARED_PREFERENCE, Activity.MODE_PRIVATE).getInt(SHARE_COUNT, 0)

    fun onClickShare(
        context: Context,
        movie: String
    ) {
        incrementShareCount(context)

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