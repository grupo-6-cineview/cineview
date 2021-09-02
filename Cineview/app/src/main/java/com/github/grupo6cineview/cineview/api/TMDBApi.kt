package com.github.grupo6cineview.cineview.api

import com.github.grupo6cineview.cineview.datamodel.NowPlaying
import com.github.grupo6cineview.cineview.features.search.data.model.Search
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies() : Response<NowPlaying>

    @GET("search/multi")
    suspend fun getSearchResult(
        @Query("query") search: String,
        @Query("page") page: Int
    ) : Response<Search>

}