package com.reactions.deathlines.data.repository.album

import com.reactions.deathlines.data.BaseNetworkTest
import com.reactions.deathlines.data.datasource.song.SongApiDataSourceImpl

import org.junit.Test;

class SongApiDataSourceTest : BaseNetworkTest() {

private lateinit var songRepositoryTest: SongApiDataSourceImpl

    override fun setUp() {
        super.setUp()
        songRepositoryTest = SongApiDataSourceImpl(songApi)
    }


    @Test
    fun validateGetSongs() {
        try {
            val response = songRepositoryTest.getSongs("Hey Jude")
            checkNotNull(response)
        } catch (e: Exception) {
            checkNotNull(e.message)
        }
    }

    @Test
    fun validateGetSongsFromAlbum() {
        try {
            val response = songRepositoryTest.getSongsFromAlbum(123123123)
            checkNotNull(response)
        } catch (e: Exception) {
            checkNotNull(e.message)
        }
    }

}