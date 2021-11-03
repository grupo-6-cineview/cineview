package com.github.grupo6cineview.cineview.features.search.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.grupo6cineview.cineview.base.BaseViewModel
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Paging.MAX_SIZE
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Paging.PAGE_SIZE
import com.github.grupo6cineview.cineview.features.search.data.model.SearchResult
import com.github.grupo6cineview.cineview.features.search.data.paging.SearchPagingSource
import com.github.grupo6cineview.cineview.features.search.domain.SearchUseCase
import kotlinx.coroutines.flow.Flow

class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : BaseViewModel() {

    fun getMovieBySearch(search: String): Flow<PagingData<SearchResult>> =
        Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                maxSize = MAX_SIZE
            )
        ) {
            SearchPagingSource(
                searchUseCase,
                search
            )
        }.flow.cachedIn(viewModelScope)
}