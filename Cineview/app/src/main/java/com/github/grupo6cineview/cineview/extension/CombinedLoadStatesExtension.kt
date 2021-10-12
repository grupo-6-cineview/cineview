package com.github.grupo6cineview.cineview.extension

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

fun CombinedLoadStates.isError() = refresh is LoadState.Error

fun CombinedLoadStates.isLoading() = source.refresh is LoadState.Loading