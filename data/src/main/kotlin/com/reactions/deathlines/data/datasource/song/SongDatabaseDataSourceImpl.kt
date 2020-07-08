package com.reactions.deathlines.data.datasource.song

import androidx.paging.DataSource
import com.reactions.deathlines.data.db.album.SongDao
import com.reactions.deathlines.data.mapper.map
import com.reactions.deathlines.domain.entity.Entity
import java.util.concurrent.Executor

/**
 * Album database data source implementation
 */
class SongDatabaseDataSourceImpl(private val songDao: SongDao,
                                 private val ioExecutor: Executor) : SongDatabaseDataSource {

    /**
     * Get all of albums from database implementation
     */
    override fun getSongs(songName: String): DataSource.Factory<Int, Entity.Song> =
            songDao.selectAllPagedFromSong(songName)
                    .map { it.map() }

    override fun getSongsFromAlbum(collectionId: Int): DataSource.Factory<Int, Entity.Song> =
            songDao.selectAllPagedFromAlbum(collectionId)
                    .map { it.map() }

    /**
     * Persist all of albums in local database implementation
     */
    override fun persist(song: List<Entity.Song>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            songDao.insert/*persist*/(song.map { it.map() })
            insertFinished()
        }
    }

    override fun deleteAlbum(album: Entity.Song) = songDao.delete(album.map())
}