package com.reactions.deathlines.data.mapper

import com.reactions.deathlines.data.db.album.SongData
import com.reactions.deathlines.domain.entity.Entity

fun SongData.Song.map() = Entity.Song(
        artistId = artistId,
        collectionId = collectionId,
        trackId = trackId,
        wrapperType = wrapperType,
        artistName = artistName,
        collectionName = collectionName,
        trackName = trackName,
        trackPrice = trackPrice,
        primaryGenreName = primaryGenreName,
        previewUrl = previewUrl,
        artworkUrl100 = artworkUrl100
)