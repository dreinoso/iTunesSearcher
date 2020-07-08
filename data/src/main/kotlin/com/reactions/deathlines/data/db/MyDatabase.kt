package com.reactions.deathlines.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reactions.deathlines.data.db.album.SongDao
import com.reactions.deathlines.data.db.album.SongData

/**
 * Database class with all of its dao classes
 */
@Database(entities = [SongData.Song::class], version = 3, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun songDao(): SongDao
}