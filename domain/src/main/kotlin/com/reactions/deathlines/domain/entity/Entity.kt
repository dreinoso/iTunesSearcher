package com.reactions.deathlines.domain.entity

/**
 * Album entity
 */
sealed class Entity {

    data class Song(
            val artistId: Long,
            val collectionId: Long,
            val trackId: Long,
            val wrapperType: String,
            val artistName: String,
            val collectionName: String?,
            val trackName: String,
            val trackPrice: String,
            val primaryGenreName: String,
            val previewUrl: String,
            val artworkUrl100: String
    ) : Entity()
}