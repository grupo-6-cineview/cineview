package com.github.grupo6cineview.cineview.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.github.grupo6cineview.cineview.db.entity.*

@Dao
interface HomeDao {

    /**
     * Carousel Movies - HomeFragment
     */
    @Insert(onConflict = REPLACE)
    suspend fun insertCarouselMovies(vararg movies: CarouselEntity)

    @Query(value = "SELECT * FROM carousel_movies")
    suspend fun getAllMoviesCarousel(): List<CarouselEntity>

    @Query(value = "SELECT * FROM carousel_movies WHERE id = :movieId")
    suspend fun getCarouselMovie(movieId: Int): CarouselEntity?

    @Delete
    suspend fun deleteCarouselMovies(vararg movies: CarouselEntity)

    /**
     * Now Playing Movies - HomeFragment
     */
    @Insert(onConflict = REPLACE)
    suspend fun insertNowPlayingMovies(vararg movies: NowPlayingEntity)

    @Query(value = "SELECT * FROM now_playing_movies")
    suspend fun getAllMoviesNowPlaying(): List<NowPlayingEntity>

    @Query(value = "SELECT * FROM now_playing_movies WHERE id = :movieId")
    suspend fun getNowPlayingMovie(movieId: Int): NowPlayingEntity?

    @Delete
    suspend fun deleteNowPlayingMovies(vararg movies: NowPlayingEntity)

    /**
     * Top Rated Movies - HomeFragment
     */
    @Insert(onConflict = REPLACE)
    suspend fun insertTopRatedMovies(vararg movies: TopRatedEntity)

    @Query(value = "SELECT * FROM top_rated_movies")
    suspend fun getAllMoviesTopRated(): List<TopRatedEntity>

    @Query(value = "SELECT * FROM top_rated_movies WHERE id = :movieId")
    suspend fun getTopRatedMovie(movieId: Int): TopRatedEntity?

    @Delete
    suspend fun deleteTopRatedMovies(vararg movies: TopRatedEntity)

    /**
     * Popular Movies - HomeFragment
     */
    @Insert(onConflict = REPLACE)
    suspend fun insertPopularMovies(vararg movies: PopularEntity)

    @Query(value = "SELECT * FROM popular_movies")
    suspend fun getAllMoviesPopular(): List<PopularEntity>

    @Query(value = "SELECT * FROM popular_movies WHERE id = :movieId")
    suspend fun getPopularMovie(movieId: Int): PopularEntity?

    @Delete
    suspend fun deletePopularMovies(vararg movies: PopularEntity)

    /**
     * Trending Movies - HomeFragment
     */
    @Insert(onConflict = REPLACE)
    suspend fun insertTrendingMovies(vararg movies: TrendingEntity)

    @Query(value = "SELECT * FROM trending_movies")
    suspend fun getAllMoviesTrending(): List<TrendingEntity>

    @Query(value = "SELECT * FROM trending_movies WHERE id = :movieId")
    suspend fun getTrendingMovie(movieId: Int): TrendingEntity?

    @Delete
    suspend fun deleteTrendingMovies(vararg movies: TrendingEntity)
}