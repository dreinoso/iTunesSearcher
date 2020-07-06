package com.reactions.deathlines.data.mapper

import com.reactions.deathlines.data.db.album.SongData
import com.reactions.deathlines.domain.entity.Entity

/**
 * Extension class to map album entity to album data
 */
fun Entity.Song.map() = SongData.Song(
        artistId = artistId,
        collectionId = collectionId,
        trackId = trackId,
        kind = kind,
        artistName = artistName,
        collectionName = collectionName,
        trackName = trackName,
        trackPrice = trackPrice,
        primaryGenreName = primaryGenreName,
        previewUrl = previewUrl,
        artworkUrl100 = artworkUrl100
)