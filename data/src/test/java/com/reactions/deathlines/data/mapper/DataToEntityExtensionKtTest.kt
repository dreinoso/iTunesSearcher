package com.reactions.deathlines.data.mapper

import org.junit.Assert.*
import org.junit.Test
import com.reactions.deathlines.domain.entity.Entity

class DataToEntityExtensionKtTest {

    val artistId = 1234.toLong()
    val collectionId = 123.toLong()
    val trackId = 123.toLong()
    val wrapperType = "song"
    val artistName = "The Beatles"
    val collectionName = "Yellow Submarine"
    val trackName = "Yellow Submarine"
    val trackPrice = "1,90"
    val primaryGenreName = "rock"
    val previewUrl = "www.123.com/foo.mp3"
    val artworkUrl100 = "www.123.com/foo.mp3"

    val songEntity = Entity.Song(artistId.toLong(),
            collectionId.toLong(),
            trackId.toLong(),
            wrapperType,
            artistName,
            collectionName,
            trackName,
            trackPrice,
            primaryGenreName,
            previewUrl,
            artworkUrl100
    )

    @Test
    fun mapFromEntity() {
        val songData = songEntity.map()
        assertEquals(songData.artistId, artistId)
        assertEquals(songData.artistName, artistName)
    }
}