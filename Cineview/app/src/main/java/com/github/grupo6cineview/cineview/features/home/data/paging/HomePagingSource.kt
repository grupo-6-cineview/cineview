package com.github.grupo6cineview.cineview.features.home.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Paging.FIRST_PAGE
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Paging.PAGE_SIZE
import com.github.grupo6cineview.cineview.extensions.ResponseApi
import com.github.grupo6cineview.cineview.extensions.getFullImageUrl
import com.github.grupo6cineview.cineview.features.home.data.model.Trending
import com.github.grupo6cineview.cineview.features.home.data.model.TrendingResult
import com.github.grupo6cineview.cineview.features.home.data.repository.HomeRepository
import okio.IOException
import retrofit2.HttpException

class HomePagingSource(
    private val homeRepository: HomeRepository,
    private val mediaType: String,
    private val timeWindow: String
) : PagingSource<Int, TrendingResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrendingResult> =
        try {
            val page = params.key ?: FIRST_PAGE

            callTrending(mediaType, timeWindow, page).let { trendingResults ->
                val prevKey =
                    if (page == FIRST_PAGE)
                        null
                    else
                        page - 1

                val nextKey =
                    if (trendingResults.isEmpty())
                        null
                    else
                        page + 1

                LoadResult.Page(
                    trendingResults,
                    prevKey,
                    nextKey
                )
            }

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }


    override fun getRefreshKey(state: PagingState<Int, TrendingResult>): Int? =
        state.anchorPosition?.let { position ->
            state.closestPageToPosition(position).let { page ->
                page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
            }
        }

    private suspend fun callTrending(mediaType: String, timeWindow: String, page:Int) : List<TrendingResult> =
        homeRepository.getTrendingMovies(mediaType, timeWindow, page).let { response ->
            when (response) {
                is ResponseApi.Success -> {
                    (response.data as Trending).results.map { it.apply { posterPath = posterPath?.getFullImageUrl(200) } }
                }

                is ResponseApi.Error -> listOf()
            }
        }

}