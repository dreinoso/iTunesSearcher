package com.reactions.deathlines.data.repository.album

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.reactions.deathlines.data.datasource.song.AlbumsApiDataSource
import com.reactions.deathlines.data.datasource.song.SongDatabaseDataSource
import com.reactions.deathlines.data.datasource.song.getSongs
import com.reactions.deathlines.domain.common.ResultState
import com.reactions.deathlines.domain.entity.Entity

class SongsRepoBoundaryCallback(
        private val apiSource: AlbumsApiDataSource,
        private val databaseSource: SongDatabaseDataSource,
        private val songName: String
) : PagedList.BoundaryCallback<Entity.Song>() {

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1
    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    /**
     * Database returned 0 items. We should send request to the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    /**
     * When all items in the database were loaded, we need to send a request to the backend for more items.
     */
    override fun onItemAtEndLoaded(itemAtEnd: Entity.Song) {
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        getSongs(apiSource, songName) { songs ->
            Log.d(this.javaClass.name, "requestAndSaveData: $songs")
            when (songs) {
                is ResultState.Success -> {
                    databaseSource.persist(songs.data) {
                        lastRequestedPage++
                        isRequestInProgress = false
                    }
                }
                is ResultState.Error -> {
                    _networkErrors.postValue(songs.throwable.message)
                    isRequestInProgress = false
                }
            }
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}