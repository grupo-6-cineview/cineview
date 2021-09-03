package com.github.grupo6cineview.cineview.api

import com.github.grupo6cineview.cineview.datamodel.NowPlaying
import com.github.grupo6cineview.cineview.datamodel.Search
import com.github.grupo6cineview.cineview.datamodel.Trending
import com.github.grupo6cineview.cineview.features.movie.data.model.movie.MovieDetails
import com.github.grupo6cineview.cineview.features.movie.data.model.tv.TvDetails
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

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingMoviesDay(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
    ) : Response<Trending>

    @GET("tv/{tv_id}")
    suspend fun getTvDetails(
        @Path("tv_id") id: Int
    ) : Response<TvDetails>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int
    ) : Response<MovieDetails>

}