package com.reactions.deathlines.data.db.album

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


sealed class SongData {

    @Entity(tableName = "song_table")
    data class Song(@ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long = 0,
                    @ColumnInfo(name = "user_id") val artistId: Long,
                    @ColumnInfo(name = "user_id") val collectionId: Long,
                    @ColumnInfo(name = "user_id") val trackId: Long,
                    @ColumnInfo(name = "user_id") val kind: String,
                    @ColumnInfo(name = "user_id") val artistName: String,
                    @ColumnInfo(name = "user_id") val collectionName: String,
                    @ColumnInfo(name = "user_id") val trackName: String,
                    @ColumnInfo(name = "user_id") val trackPrice: String,
                    @ColumnInfo(name = "user_id") val primaryGenreName: String,
                    @ColumnInfo(name = "user_id") val previewUrl: String,
                    @ColumnInfo(name = "title") val artworkUrl100: String) : SongData()

    @Entity(tableName = "album_photo_table")
    data class Photo(@ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) val id: Long,
                     @ColumnInfo(name = "album_id") val albumId: Long,
                     @ColumnInfo(name = "title") val title: String,
                     @ColumnInfo(name = "url") val url: String,
                     @ColumnInfo(name = "thumbnail_url") val thumbnailUrl: String) : SongData()
}