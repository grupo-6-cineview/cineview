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

    @Query(value = "SELECT * FROM favorite_movies")
    suspend fun getFavorites(): List<FavoriteEntity>

    @Transaction
    @Query(value = "SELECT * FROM favorite_movies WHERE movie_id = :movieId")
    suspend fun getFavoriteWithCasts(movieId: Int) : FavoriteWithCast?

    @Transaction
    @Query(value = "SELECT * FROM favorite_movies WHERE movie_id = :movieId")
    suspend fun getFavoriteWithSimilars(movieId: Int) : FavoriteWithSimilar?

    @Query(value = "DELETE FROM favorite_movies WHERE movie_id = :movieId")
    suspend fun deleteFavorites(movieId: Int)

    @Query(value = "DELETE FROM cast_table WHERE cast_id = :castId")
    suspend fun deleteCasts(castId: Int)

    @Query(value = "DELETE FROM similar_movies WHERE similar_id = :similarId")
    suspend fun deleteSimilars(similarId: Int)
}