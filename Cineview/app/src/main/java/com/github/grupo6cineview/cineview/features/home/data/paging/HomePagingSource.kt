package com.github.grupo6cineview.cineview.features.home.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Paging.FIRST_PAGE
import com.github.grupo6cineview.cineview.features.home.data.model.HomeResult
import com.github.grupo6cineview.cineview.features.home.domain.HomeIntent
import com.github.grupo6cineview.cineview.features.home.domain.HomeUseCase

class HomePagingSource(
    private val homeUseCase: HomeUseCase,
    private val intent: HomeIntent
) : PagingSource<Int, HomeResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeResult> =
        try {
            val page = params.key ?: if (intent == HomeIntent.NowPlaying) FIRST_PAGE + 1 else FIRST_PAGE

            callMovies(intent, page)?.let { listResults ->
                val prevPage =
                    if (intent == HomeIntent.NowPlaying && page == FIRST_PAGE + 1)
                        null
                    else if (page == FIRST_PAGE)
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
            } ?: throw Exception("HomeUseCase - Response Api Error")

        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, HomeResult>): Int? =
        state.anchorPosition?.let { position ->
            state.closestPageToPosition(position).let { page ->
                page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
            }
        }

    private suspend fun callMovies(intent: HomeIntent, page:Int) : List<HomeResult>? = homeUseCase.getMovies(intent, page)
}