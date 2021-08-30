package com.github.grupo6cineview.cineview.extensions

sealed class Command {
    class Loading(val value: Boolean): Command()
    class Error(val error: Int): Command()
}