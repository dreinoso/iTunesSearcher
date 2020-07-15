package com.reactions.deathlines.presentation.ui.home

import com.reactions.deathlines.domain.usecase.album.GetSongsFromAlbumUseCaseImpl
import com.reactions.deathlines.presentation.ui.albumdetail.AlbumDetailsViewModel
import com.reactions.deathlines.presentation.ui.base.BaseUnitTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock

class AlbumDetailsViewModelTest : BaseUnitTest() {

    @Mock
    private lateinit var getSongsFromAlbumUseCaseImpl: GetSongsFromAlbumUseCaseImpl

    private lateinit var albumDetailsViewModelTest: AlbumDetailsViewModel

    @Before
    override fun setUp() {
        super.setUp()
        albumDetailsViewModelTest = AlbumDetailsViewModel(getSongsFromAlbumUseCaseImpl)
    }

    @Test
    fun getSongs() {
        albumDetailsViewModelTest.getAlbumSongs(testInt)
        assertNotNull(albumDetailsViewModelTest.albumSongsLiveData)
    }
}