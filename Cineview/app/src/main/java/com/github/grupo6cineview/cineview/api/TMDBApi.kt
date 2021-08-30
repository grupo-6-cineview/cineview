package com.github.grupo6cineview.cineview.api

import com.github.grupo6cineview.cineview.datamodel.NowPlaying
import retrofit2.Response
import retrofit2.http.GET

interface TMDBApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): Response<NowPlaying>
}