package com.reactions.deathlines.data.datasource.album

import androidx.paging.DataSource
import com.reactions.deathlines.data.db.album.SongDao
import com.reactions.deathlines.data.mapper.map
import com.reactions.deathlines.domain.entity.Entity
import java.util.concurrent.Executor

/**
 * Album database data source implementation
 */
class AlbumsDatabaseDataSourceImpl(private val albumDao: SongDao,
                                   private val ioExecutor: Executor) : AlbumsDatabaseDataSource {

    /**
     * Get all of albums from database implementation
     */
    override fun getSongs(): DataSource.Factory<Int, Entity.Song> =
            albumDao.selectAllPaged()
                    .map { it.map() }

    /**
     * Persist all of albums in local database implementation
     */
    override fun persist(albums: List<Entity.Song>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            albumDao.insert/*persist*/(albums.map { it.map() })
            insertFinished()
        }
    }

    override fun deleteAlbum(album: Entity.Song) = albumDao.delete(album.map())
}