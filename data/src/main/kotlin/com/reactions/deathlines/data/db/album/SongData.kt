package com.reactions.deathlines.data.db.album

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


sealed class SongData {

    @Entity(tableName = "song_table")
    data class Song(@ColumnInfo(name = "trackId") @PrimaryKey(autoGenerate = false) val trackId: Long,
                    @ColumnInfo(name = "artistId") val artistId: Long,
                    @ColumnInfo(name = "collectionId") val collectionId: Long,
                    @ColumnInfo(name = "kind") val kind: String,
                    @ColumnInfo(name = "artistName") val artistName: String,
                    @ColumnInfo(name = "collectionName") val collectionName: String,
                    @ColumnInfo(name = "trackName") val trackName: String,
                    @ColumnInfo(name = "trackPrice") val trackPrice: String,
                    @ColumnInfo(name = "primaryGenreName") val primaryGenreName: String,
                    @ColumnInfo(name = "previewUrl") val previewUrl: String,
                    @ColumnInfo(name = "artworkUrl100") val artworkUrl100: String) : SongData()

    @Entity(tableName = "album_photo_table")
    data class Photo(@ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) val id: Long,
                     @ColumnInfo(name = "album_id") val albumId: Long,
                     @ColumnInfo(name = "title") val title: String,
                     @ColumnInfo(name = "url") val url: String,
                     @ColumnInfo(name = "thumbnail_url") val thumbnailUrl: String) : SongData()
}