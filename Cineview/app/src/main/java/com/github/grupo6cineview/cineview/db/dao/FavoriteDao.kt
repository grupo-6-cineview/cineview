package com.github.grupo6cineview.cineview.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.github.grupo6cineview.cineview.db.entity.favorite.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertFavorites(vararg favorites: FavoriteEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insertCasts(vararg casts: CastEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insertSimilars(vararg similars: SimilarEntity)

    @Transaction
    @Query(value = "SELECT * FROM favorite_movies WHERE movie_id = :movieId")
    suspend fun getFavoriteWithCasts(movieId: Int) : FavoriteWithCast?

    @Transaction
    @Query(value = "SELECT * FROM favorite_movies WHERE movie_id = :movieId")
    suspend fun getFavoriteWithSimilars(movieId: Int) : FavoriteWithSimilar?

    @Delete
    suspend fun deleteFavorites(vararg favorites: FavoriteEntity)

    @Delete
    suspend fun deleteCasts(vararg casts: CastEntity)

    @Delete
    suspend fun deleteSimilars(vararg similars: SimilarEntity)
}