package com.github.grupo6cineview.cineview.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.github.grupo6cineview.cineview.db.entity.favorite.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertFavorite(favorites: FavoriteEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insertCasts(casts: List<CastEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun insertSimilars(similars: List<SimilarEntity>)

    @Transaction
    @Query(value = "SELECT * FROM favorite_movies WHERE movie_id = :movieId")
    suspend fun getFavoriteWithCasts(movieId: Int) : FavoriteWithCast?

    @Transaction
    @Query(value = "SELECT * FROM favorite_movies WHERE movie_id = :movieId")
    suspend fun getFavoriteWithSimilars(movieId: Int) : FavoriteWithSimilar?

    @Delete
    suspend fun deleteFavorites(favorites: FavoriteEntity)

    @Delete
    suspend fun deleteCasts(casts: List<CastEntity>)

    @Delete
    suspend fun deleteSimilars(similars: List<SimilarEntity>)
}