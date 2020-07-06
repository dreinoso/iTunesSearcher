package com.reactions.deathlines.data.mapper

import com.reactions.deathlines.data.db.album.SongData
import com.reactions.deathlines.domain.entity.Entity

/**
 * Extension class to map album data to album entity
 */
fun SongData.Song.map() = Entity.Song(
        id = id,
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