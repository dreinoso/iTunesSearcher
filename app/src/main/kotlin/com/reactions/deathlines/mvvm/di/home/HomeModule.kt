package com.reactions.deathlines.mvvm.di.home

import dagger.Module
import dagger.Provides
import com.com.reactions.deathlines.data.api.AlbumApi
import com.reactions.deathlines.data.datasource.album.AlbumsApiDataSource
import com.reactions.deathlines.data.datasource.album.AlbumsApiDataSourceImpl
import com.reactions.deathlines.data.datasource.album.AlbumsDatabaseDataSource
import com.reactions.deathlines.data.datasource.album.AlbumsDatabaseDataSourceImpl
import com.reactions.deathlines.data.db.album.SongDao
import com.reactions.deathlines.data.repository.album.AlbumsRepositoryImpl
import com.reactions.deathlines.domain.repository.album.SongsRepository
import com.reactions.deathlines.domain.usecase.album.GetSongsFromSongUseCase
import com.reactions.deathlines.domain.usecase.album.GetSongsFromSongCaseImpl
import com.reactions.deathlines.presentation.common.transformer.AsyncFTransformer
import com.reactions.deathlines.presentation.common.transformer.AsyncSTransformer
import java.util.concurrent.Executors

@Module
class HomeModule {

    @Provides
    //@PerFragment
    fun provideDatabaseSource(albumDao: SongDao): AlbumsDatabaseDataSource =
            AlbumsDatabaseDataSourceImpl(albumDao, Executors.newSingleThreadExecutor())

    @Provides
    //@PerFragment
    fun provideApiSource(api: AlbumApi): AlbumsApiDataSource = AlbumsApiDataSourceImpl(api)

    @Provides
    //@PerFragment
    fun provideRepository(
            apiSource: AlbumsApiDataSource,
            databaseSource: AlbumsDatabaseDataSource
    ): SongsRepository {
        return AlbumsRepositoryImpl(apiSource, databaseSource)
    }

    @Provides
    //@PerFragment
    fun provideGetAlbumsUseCaseImpl(repository: SongsRepository): GetSongsFromSongUseCase = GetSongsFromSongCaseImpl(AsyncFTransformer(), AsyncSTransformer(), AsyncSTransformer(), repository)
}
