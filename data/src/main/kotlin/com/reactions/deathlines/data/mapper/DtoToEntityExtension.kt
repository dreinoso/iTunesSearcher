package com.reactions.deathlines.data.mapper

import com.com.reactions.deathlines.data.api.AlbumApi
import com.reactions.deathlines.domain.entity.Entity

/**
 * Extension class to map album dto to album entity
 */
fun AlbumApi.Dto.Song.map() = Entity.Song(
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