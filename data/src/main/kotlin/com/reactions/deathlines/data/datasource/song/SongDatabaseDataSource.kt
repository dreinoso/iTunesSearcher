package com.reactions.deathlines.data.datasource.song

import androidx.paging.DataSource
import io.reactivex.Single
import com.reactions.deathlines.data.datasource.BaseDataSource
import com.reactions.deathlines.domain.entity.Entity

/**
 * Album database data source
 */
interface SongDatabaseDataSource : BaseDataSource {

    /**
     * Get all of albums from database implementation
     */
    fun getSongs(songNAme: String): DataSource.Factory<Int, Entity.Song>

    fun getSongsFromAlbum(collectionId: Int): DataSource.Factory<Int, Entity.Song>

    /**
     * Persist all of albums in local database
     */
    fun persist(albums: List<Entity.Song>, insertFinished: () -> Unit)

    fun deleteAlbum(album: Entity.Song): Single<Int>
}