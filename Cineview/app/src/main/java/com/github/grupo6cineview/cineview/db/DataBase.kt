package com.github.grupo6cineview.cineview.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.grupo6cineview.cineview.db.dao.HomeDao
import com.github.grupo6cineview.cineview.db.entity.*

@Database(
    entities = [
        CarouselEntity::class,
        NowPlayingEntity::class,
        TopRatedEntity::class,
        PopularEntity::class,
        TrendingEntity::class
   ],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun homeDao(): HomeDao

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