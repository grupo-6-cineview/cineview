package com.github.grupo6cineview.cineview.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.github.grupo6cineview.cineview.db.entity.*

@Dao
interface HomeDao {

    /**
     * Carousel Movies - HomeFragment
     */
    @Insert(onConflict = ABORT)
    suspend fun insertCarouselMovies(movies: List<CarouselEntity>)

    @Query(value = "SELECT * FROM carousel_movies")
    suspend fun getAllMoviesCarousel(): List<CarouselEntity>

    @Query(value = "SELECT * FROM carousel_movies WHERE movie_id = :movieId")
    suspend fun getCarouselMovie(movieId: Int): CarouselEntity?

    @Query(value = "DELETE FROM carousel_movies")
    suspend fun resetCarouselMovies()

    /**
     * Now Playing Movies - HomeFragment
     */
    @Insert(onConflict = ABORT)
    suspend fun insertNowPlayingMovies(movies: List<NowPlayingEntity>)

    @Query(value = "SELECT * FROM now_playing_movies")
    suspend fun getAllMoviesNowPlaying(): List<NowPlayingEntity>

    @Query(value = "SELECT * FROM now_playing_movies WHERE movie_id = :movieId")
    suspend fun getNowPlayingMovie(movieId: Int): NowPlayingEntity?

    @Query(value = "DELETE FROM now_playing_movies")
    suspend fun resetNowPlayingMovies()

    /**
     * Top Rated Movies - HomeFragment
     */
    @Insert(onConflict = ABORT)
    suspend fun insertTopRatedMovies(movies: List<TopRatedEntity>)

    @Query(value = "SELECT * FROM top_rated_movies")
    suspend fun getAllMoviesTopRated(): List<TopRatedEntity>

    @Query(value = "SELECT * FROM top_rated_movies WHERE movie_id = :movieId")
    suspend fun getTopRatedMovie(movieId: Int): TopRatedEntity?

    @Query(value = "DELETE FROM top_rated_movies")
    suspend fun resetTopRatedMovies()

    /**
     * Popular Movies - HomeFragment
     */
    @Insert(onConflict = ABORT)
    suspend fun insertPopularMovies(movies: List<PopularEntity>)

    @Query(value = "SELECT * FROM popular_movies")
    suspend fun getAllMoviesPopular(): List<PopularEntity>

    @Query(value = "SELECT * FROM popular_movies WHERE movie_id = :movieId")
    suspend fun getPopularMovie(movieId: Int): PopularEntity?

    @Query(value = "DELETE FROM popular_movies")
    suspend fun resetPopularMovies()

    /**
     * Trending Movies - HomeFragment
     */
    @Insert(onConflict = ABORT)
    suspend fun insertTrendingMovies(movies: List<TrendingEntity>)

    @Query(value = "SELECT * FROM trending_movies")
    suspend fun getAllMoviesTrending(): List<TrendingEntity>

    @Query(value = "SELECT * FROM trending_movies WHERE movie_id = :movieId")
    suspend fun getTrendingMovie(movieId: Int): TrendingEntity?

    @Query(value = "DELETE FROM trending_movies")
    suspend fun resetTrendingMovies()
}