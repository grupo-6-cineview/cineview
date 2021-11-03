package com.github.grupo6cineview.cineview.api

import com.github.grupo6cineview.cineview.utils.ConstantsApp.Api.PATH_TRENDING_MOVIE
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Api.PATH_TRENDING_WEEK
import com.github.grupo6cineview.cineview.features.home.data.model.HomeResponse
import com.github.grupo6cineview.cineview.features.movie.data.model.cast.CastResponse
import com.github.grupo6cineview.cineview.features.movie.data.model.details.DetailsResponse
import com.github.grupo6cineview.cineview.features.movie.data.model.genre.GenresResponse
import com.github.grupo6cineview.cineview.features.movie.data.model.similar.SimilarResponse
import com.github.grupo6cineview.cineview.features.search.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    // Home
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ) : Response<HomeResponse>

    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("vote_count.gte") baseVoteCount: Int = DISCOVER_BASE_VOTE_COUNT,
        @Query("sort_by") sortBy: String = DISCOVER_SORT_BY
    ) : Response<HomeResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ) : Response<HomeResponse>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingMovies(
        @Path("media_type") mediaType: String = PATH_TRENDING_MOVIE,
        @Path("time_window") timeWindow: String = PATH_TRENDING_WEEK,
        @Query("page") page: Int
    ) : Response<HomeResponse>

    // Search
    @GET("search/movie")
    suspend fun getSearchResult(
        @Query("query") search: String,
        @Query("page") page: Int
    ) : Response<SearchResponse>

    // Details
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int
    ) : Response<DetailsResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") id: Int
    ) : Response<CastResponse>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") id: Int
    ) : Response<SimilarResponse>

    // Genres
    @GET("genre/movie/list")
    suspend fun getAllGenres() : Response<GenresResponse>

    companion object {
        private const val DISCOVER_BASE_VOTE_COUNT = 1000
        private const val DISCOVER_SORT_BY = "vote_count.desc"
    }
}