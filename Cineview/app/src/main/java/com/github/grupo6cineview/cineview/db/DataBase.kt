package com.github.grupo6cineview.cineview.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.grupo6cineview.cineview.db.dao.FavoriteDao
import com.github.grupo6cineview.cineview.db.dao.HomeDao
import com.github.grupo6cineview.cineview.db.entity.*
import com.github.grupo6cineview.cineview.db.entity.favorite.CastEntity
import com.github.grupo6cineview.cineview.db.entity.favorite.FavoriteEntity
import com.github.grupo6cineview.cineview.db.entity.favorite.SimilarEntity

@Database(
    entities = [
        CarouselEntity::class,
        NowPlayingEntity::class,
        TopRatedEntity::class,
        PopularEntity::class,
        TrendingEntity::class,
        FavoriteEntity::class,
        CastEntity::class,
        SimilarEntity::class
   ],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun homeDao(): HomeDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(context: Context): DataBase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "app_database"
                ).build().let { instance ->
                    INSTANCE = instance
                    return@synchronized instance
                }
            }
    }
}