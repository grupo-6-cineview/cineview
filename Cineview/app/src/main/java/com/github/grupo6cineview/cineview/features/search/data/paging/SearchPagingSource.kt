package com.github.grupo6cineview.cineview.features.search.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Paging.FIRST_PAGE
import com.github.grupo6cineview.cineview.features.home.domain.HomeIntent
import com.github.grupo6cineview.cineview.features.home.domain.HomeUseCase
import com.github.grupo6cineview.cineview.features.search.data.model.SearchResult
import com.github.grupo6cineview.cineview.features.search.domain.SearchUseCase
import java.lang.Exception

class SearchPagingSource(
    private val searchUseCase: SearchUseCase,
    private val search: String
) : PagingSource<Int, SearchResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResult> =
        try {
            val page = params.key ?: FIRST_PAGE

            callMoviesBySearch(search, page)?.let { listResults ->
                val prevPage =
                    if (page == FIRST_PAGE)
                        null
                    else
                        page - 1

                val nextPage =
                    if (listResults.isEmpty())
                        null
                    else
                        page + 1

                LoadResult.Page(
                    listResults,
                    prevPage,
                    nextPage
                )
            } ?: throw Exception("SearchUseCase - Response Api Error")
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, SearchResult>): Int? =
        state.anchorPosition?.let { position ->
            state.closestPageToPosition(position).let { page ->
                page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
            }
        }

    private suspend fun callMoviesBySearch(search: String, page: Int): List<SearchResult>? =
        searchUseCase.getMovieBySearch(search, page)
}