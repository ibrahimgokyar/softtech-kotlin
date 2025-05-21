package com.android.roomornek.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.roomornek.dao.NoteDao
import com.android.roomornek.entities.Note


@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun notedao(): NoteDao

}