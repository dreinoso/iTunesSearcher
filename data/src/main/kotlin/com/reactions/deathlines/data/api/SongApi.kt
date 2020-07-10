package com.com.reactions.deathlines.data.api

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Albums api
 */
interface SongApi {

    @GET("/search")
    fun  getSongs(
            @Query("term") term: String,
            @Query("mediaType") mediaType: String,
            @Query("limit") pageSize: Int)
            : Single<Dto.SearchResponse>

    @GET("/lookup")
    fun getSongsFromAlbum(
            @Query("id") id: Int,
            @Query("entity") entity: String)
            : Single<Dto.SearchResponse>

    sealed class Dto {
        data class Song(
                @SerializedName("artistId") val artistId: Long,
                @SerializedName("collectionId") val collectionId: Long,
                @SerializedName("trackId") val trackId: Long,
                @SerializedName("wrapperType") val wrapperType: String,
                @SerializedName("artistName") val artistName: String,
                @SerializedName("collectionName") val collectionName: String,
                @SerializedName("trackName") val trackName: String,
                @SerializedName("trackPrice") val trackPrice: String,
                @SerializedName("primaryGenreName") val primaryGenreName: String,
                @SerializedName("previewUrl") val previewUrl: String,
                @SerializedName("artworkUrl100") val artworkUrl100: String
        ) : Dto()

        data class SearchResponse(
                @SerializedName("resultCount") val resultsCount: Long,
                @SerializedName("results") val results: List<Song>
        ) : Dto()
    }
}