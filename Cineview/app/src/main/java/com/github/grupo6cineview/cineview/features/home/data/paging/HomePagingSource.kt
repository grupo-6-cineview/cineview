package com.github.grupo6cineview.cineview.features.home.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Paging.FIRST_PAGE
import com.github.grupo6cineview.cineview.features.home.data.model.HomeViewParams
import com.github.grupo6cineview.cineview.features.home.domain.HomeIntent
import com.github.grupo6cineview.cineview.features.home.domain.HomeUseCase

class HomePagingSource(
    private val homeUseCase: HomeUseCase,
    private val intent: HomeIntent
) : PagingSource<Int, HomeViewParams>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeViewParams> =
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

    override fun getRefreshKey(state: PagingState<Int, HomeViewParams>): Int? =
        state.anchorPosition?.let { position ->
            state.closestPageToPosition(position).let { page ->
                page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
            }
        }

    private suspend fun callMovies(intent: HomeIntent, page:Int) : List<HomeViewParams>? = homeUseCase.getMovies(intent, page)
}