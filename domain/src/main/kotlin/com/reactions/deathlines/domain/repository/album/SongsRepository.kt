package com.reactions.deathlines.domain.repository.album

import androidx.paging.PagedList
import io.reactivex.Flowable
import io.reactivex.Single
import com.reactions.deathlines.domain.common.ResultState
import com.reactions.deathlines.domain.entity.Entity
import com.reactions.deathlines.domain.repository.BaseRepository

interface SongsRepository : BaseRepository {

    fun getSongFromTrackId(trackId: Int): Single<ResultState<Entity.Song>>

    fun getSongs(songName: String): Flowable<ResultState<PagedList<Entity.Song>>>

    fun getSongsFromAlbum(collectionId: Int): Flowable<ResultState<PagedList<Entity.Song>>>

}