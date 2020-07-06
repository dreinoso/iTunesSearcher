package com.reactions.deathlines.data.datasource.album

import android.annotation.SuppressLint
import io.reactivex.Single
import com.reactions.deathlines.data.datasource.BaseDataSource
import com.reactions.deathlines.domain.common.ResultState
import com.reactions.deathlines.domain.entity.Entity

@SuppressLint("CheckResult")
fun getSongs(
        apiSource: AlbumsApiDataSource,
        songName: String,
        onResult: (result: ResultState<List<Entity.Song>>) -> Unit
) {
    apiSource.getSongs(songName)
            .subscribe({ data ->
                onResult(ResultState.Success(data))
            }, { throwable ->
                onResult(ResultState.Error(throwable, null))
            })
}

@SuppressLint("CheckResult")
fun getSongsFromAlbum(
        apiSource: AlbumsApiDataSource,
        collectionId: Int,
        onResult: (result: ResultState<List<Entity.Song>>) -> Unit
) {
    apiSource.getSongsFromAlbum(collectionId)
            .subscribe({ data ->
                onResult(ResultState.Success(data))
            }, { throwable ->
                onResult(ResultState.Error(throwable, null))
            })
}

/**
 * Album network data source
 */
interface AlbumsApiDataSource : BaseDataSource {

    fun getSongs(song: String): Single<List<Entity.Song>>

    fun getSongsFromAlbum(collectionId: Int): Single<List<Entity.Song>>
}