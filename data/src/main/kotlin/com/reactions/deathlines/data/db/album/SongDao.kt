package com.reactions.deathlines.data.db.album

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Flowable
import com.reactions.deathlines.data.db.BaseDao

@Dao
interface SongDao : BaseDao<SongData.Song> {

    @Query("SELECT * FROM song_table WHERE id = :id")
    override fun select(id: Long): Flowable<SongData.Song>

    @Query("SELECT * FROM song_table ORDER BY id")
    override fun selectAllPaged(): DataSource.Factory<Int, SongData.Song>

    @Query("DELETE FROM song_table")
    override fun truncate()

    @Transaction
    fun replace(songs: List<SongData.Song>) {
        val firstId: Long = songs.firstOrNull()?.id ?: run {
            0L
        }

        val lastId = songs.lastOrNull()?.id ?: run {
            Long.MAX_VALUE
        }

        deleteRange(firstId, lastId)
        insert(songs)
    }

    @Query("DELETE FROM song_table WHERE id BETWEEN :firstId AND :lastId")
    fun deleteRange(firstId: Long, lastId: Long)
}