package com.reactions.deathlines.data.datasource.song

import io.reactivex.Single
import com.com.reactions.deathlines.data.api.SongApi
import com.reactions.deathlines.data.common.extension.applyIoScheduler
import com.reactions.deathlines.data.mapper.map
import com.reactions.deathlines.domain.entity.Entity

class SongApiDataSourceImpl(private val api: SongApi) : AlbumsApiDataSource {

    private val LIMIT = 5
    private val MEDIA_TYPE = "music"
    private val ENTITY = "song"

    override fun getSongs(song: String): Single<List<Entity.Song>> =
            api.getSongs(song, MEDIA_TYPE, LIMIT)
                    .applyIoScheduler()
                    .map { item -> item.results.map { it.map() } }

    override fun getSongsFromAlbum(collectionId: Int): Single<List<Entity.Song>> =
            api.getSongsFromAlbum(collectionId, ENTITY)
                    .applyIoScheduler()
                    .map { item ->
                        item.results.filter { it.wrapperType != "collection" }.map {

                                it.map()

                        }}
}