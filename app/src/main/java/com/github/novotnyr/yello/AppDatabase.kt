package com.github.novotnyr.yello

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null

        operator fun invoke(context: Context): AppDatabase =
            db ?: synchronized(this) {
                db ?: context.buildDatabase()
                    .also {
                        db = it
                    }
            }

        private fun Context.buildDatabase(): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "note-database"
            ).build()
        }
    }
}