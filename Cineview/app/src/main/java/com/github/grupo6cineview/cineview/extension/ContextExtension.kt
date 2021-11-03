package com.github.grupo6cineview.cineview.extension

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.content.res.AppCompatResources

fun Context.getDrawable2(id: Int) : Drawable? = AppCompatResources.getDrawable(this, id)

fun Context.hideKeyboard(view: View) =
    try {
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(
                view.windowToken,
                0
            )
    } catch (e: RuntimeException) {
        e.printStackTrace()
    }

fun Context.appIsConnected() =
    (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).let { manager ->
        val capabilities = manager.getNetworkCapabilities(manager.activeNetwork)
        capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }