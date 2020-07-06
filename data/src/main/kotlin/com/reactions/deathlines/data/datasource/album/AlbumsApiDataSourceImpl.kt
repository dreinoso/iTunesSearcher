package com.reactions.deathlines.data.datasource.album

import io.reactivex.Single
import com.com.reactions.deathlines.data.api.AlbumApi
import com.reactions.deathlines.data.common.extension.applyIoScheduler
import com.reactions.deathlines.data.mapper.map
import com.reactions.deathlines.domain.entity.Entity

class AlbumsApiDataSourceImpl(private val api: AlbumApi) : AlbumsApiDataSource {

    private val LIMIT = 100
    private val MEDIA_TYPE = "music"
    private val ENTITY = "song"

    override fun getSongs(song: String): Single<List<Entity.Song>> =
            api.getSongs(song, MEDIA_TYPE, LIMIT)
                    .applyIoScheduler()
                    .map { item -> item.map { it.map() } }

    override fun getSongsFromAlbum(collectionId: Int): Single<List<Entity.Song>> =
            api.getSongsFromAlbum(collectionId, ENTITY)
                    .applyIoScheduler()
                    .map { item -> item.map { it.map() } }
}