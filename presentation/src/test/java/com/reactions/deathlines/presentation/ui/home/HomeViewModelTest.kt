package com.reactions.deathlines.presentation.ui.home

import com.reactions.deathlines.domain.usecase.album.GetSongsFromSongUseCase
import com.reactions.deathlines.presentation.ui.base.BaseUnitTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock

class HomeViewModelTest : BaseUnitTest() {

    @Mock
    private lateinit var getSongsFromSongUseCase: GetSongsFromSongUseCase

    private lateinit var homeViewModel : HomeViewModel

    @Before
    override fun setUp() {
        super.setUp()
        homeViewModel = HomeViewModel(getSongsFromSongUseCase)
    }

    @Test
    fun getSongs() {
            homeViewModel.getSongs(testString)
            assertNotNull(homeViewModel.albumsLiveData)
        }
}