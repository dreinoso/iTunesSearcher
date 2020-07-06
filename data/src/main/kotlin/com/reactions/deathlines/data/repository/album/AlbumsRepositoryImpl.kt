package com.reactions.deathlines.data.repository.album

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import com.reactions.deathlines.data.common.extension.applyIoScheduler
import com.reactions.deathlines.data.datasource.album.AlbumsApiDataSource
import com.reactions.deathlines.data.datasource.album.AlbumsDatabaseDataSource
import com.reactions.deathlines.data.repository.BaseRepositoryImpl
import com.reactions.deathlines.domain.common.ResultState
import com.reactions.deathlines.domain.entity.Entity
import com.reactions.deathlines.domain.repository.album.SongsRepository

class AlbumsRepositoryImpl(
        private val apiSource: AlbumsApiDataSource,
        private val databaseSource: AlbumsDatabaseDataSource
) : BaseRepositoryImpl<Entity.Song>(), SongsRepository {

    override fun getSongs(songName: String): Flowable<ResultState<PagedList<Entity.Song>>> {
        val dataSourceFactory = databaseSource.getSongs()
        val boundaryCallback = SongsRepoBoundaryCallback(apiSource, databaseSource, songName)
        val data = RxPagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .buildFlowable(BackpressureStrategy.BUFFER)

        return data
                .applyIoScheduler()
                .map { d ->
                    if (d.size > 0)
                        ResultState.Success(d) as ResultState<PagedList<Entity.Song>>
                    else
                        ResultState.Loading(d) as ResultState<PagedList<Entity.Song>>
                }
                .onErrorReturn { e -> ResultState.Error(e, null) }
    }

    override fun getSongsFromAlbum(collectionId: Int): Flowable<ResultState<PagedList<Entity.Song>>> {
        val dataSourceFactory = databaseSource.getSongs()
        val boundaryCallback = SongsAlbumRepoBoundaryCallback(apiSource, databaseSource, collectionId)
        val data = RxPagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .buildFlowable(BackpressureStrategy.BUFFER)

        return data
                .applyIoScheduler()
                .map { d ->
                    if (d.size > 0)
                        ResultState.Success(d) as ResultState<PagedList<Entity.Song>>
                    else
                        ResultState.Loading(d) as ResultState<PagedList<Entity.Song>>
                }
                .onErrorReturn { e -> ResultState.Error(e, null) }
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}