package com.schwarckstudio.lionfitness.wear.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WearWorkoutEntity::class], version = 1, exportSchema = false)
abstract class WearWorkoutDatabase : RoomDatabase() {
    abstract fun wearWorkoutDao(): WearWorkoutDao

    companion object {
        @Volatile
        private var INSTANCE: WearWorkoutDatabase? = null

        fun getDatabase(context: Context): WearWorkoutDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WearWorkoutDatabase::class.java,
                    "wear_workout_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
