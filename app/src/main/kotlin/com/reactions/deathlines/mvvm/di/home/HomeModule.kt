package com.reactions.deathlines.mvvm.di.home

import dagger.Module
import dagger.Provides
import com.com.reactions.deathlines.data.api.SongApi
import com.reactions.deathlines.data.datasource.song.AlbumsApiDataSource
import com.reactions.deathlines.data.datasource.song.SongApiDataSourceImpl
import com.reactions.deathlines.data.datasource.song.SongDatabaseDataSource
import com.reactions.deathlines.data.datasource.song.SongDatabaseDataSourceImpl
import com.reactions.deathlines.data.db.album.SongDao
import com.reactions.deathlines.data.repository.album.SongsRepositoryImpl
import com.reactions.deathlines.domain.repository.album.SongsRepository
import com.reactions.deathlines.domain.usecase.album.GetSongsFromAlbumUseCase
import com.reactions.deathlines.domain.usecase.album.GetSongsFromAlbumUseCaseImpl
import com.reactions.deathlines.domain.usecase.album.GetSongsFromSongUseCase
import com.reactions.deathlines.domain.usecase.album.GetSongsFromSongCaseImpl
import java.util.concurrent.Executors

@Module
class HomeModule {

    @Provides
    //@PerFragment
    fun provideDatabaseSource(albumDao: SongDao): SongDatabaseDataSource =
            SongDatabaseDataSourceImpl(albumDao, Executors.newSingleThreadExecutor())

    @Provides
    //@PerFragment
    fun provideApiSource(api: SongApi): AlbumsApiDataSource = SongApiDataSourceImpl(api)

    @Provides
    //@PerFragment
    fun provideRepository(
            apiSource: AlbumsApiDataSource,
            databaseSource: SongDatabaseDataSource
    ): SongsRepository {
        return SongsRepositoryImpl(apiSource, databaseSource)
    }

    @Provides
    fun provideGetSongsFromSongUseCaseImpl(repository: SongsRepository): GetSongsFromSongUseCase = GetSongsFromSongCaseImpl(repository)

    @Provides
    fun provideGetSongsFromAlbumUseCaseImpl(repository: SongsRepository): GetSongsFromAlbumUseCase = GetSongsFromAlbumUseCaseImpl(repository)
}
