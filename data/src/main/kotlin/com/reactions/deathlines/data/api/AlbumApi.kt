package com.com.reactions.deathlines.data.api

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Albums api
 */
interface AlbumApi {

    @GET("/search")
    fun  getSongs(
            @Query("term") term: String,
            @Query("mediaType") mediaType: String,
            @Query("limit") pageSize: Int)
            : Single<List<Dto.Song>>

    @GET("/lookup")
    fun getSongsFromAlbum(
            @Query("id") page: Int,
            @Query("entity") entity: String)
            : Single<List<Dto.Song>>

    sealed class Dto {
        data class Song(
                @SerializedName("artistId") val artistId: Long,
                @SerializedName("collectionId") val collectionId: Long,
                @SerializedName("trackId") val trackId: Long,
                @SerializedName("kind") val kind: String,
                @SerializedName("artistName") val artistName: String,
                @SerializedName("collectionName") val collectionName: String,
                @SerializedName("trackName") val trackName: String,
                @SerializedName("trackPrice") val trackPrice: String,
                @SerializedName("primaryGenreName") val primaryGenreName: String,
                @SerializedName("previewUrl") val previewUrl: String,
                @SerializedName("artworkUrl100") val artworkUrl100: String
        ) : Dto()
    }
}