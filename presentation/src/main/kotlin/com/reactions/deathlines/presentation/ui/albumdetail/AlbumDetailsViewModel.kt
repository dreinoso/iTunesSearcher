package com.reactions.deathlines.presentation.ui.albumdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import io.reactivex.disposables.Disposable
import com.reactions.deathlines.domain.common.ResultState
import com.reactions.deathlines.domain.entity.Entity
import com.reactions.deathlines.domain.usecase.album.GetSongFromTrackIdUseCase
import com.reactions.deathlines.domain.usecase.album.GetSongsFromAlbumUseCase
import com.reactions.deathlines.presentation.common.OperationLiveData
import com.reactions.deathlines.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class AlbumDetailsViewModel @Inject constructor(
        private val getSongFromTrackIdUseCase: GetSongFromTrackIdUseCase,
        private val getSongsFromAlbumUseCase: GetSongsFromAlbumUseCase) : BaseViewModel() {

    private var tempDisposable: Disposable? = null
    private val songNameLiveData = MutableLiveData<String>()
    private var trackId = 0
    private val currentSongLiveData = MutableLiveData<ResultState<Entity.Song>>()

    val albumSongsLiveData: LiveData<ResultState<PagedList<Entity.Song>>> = Transformations.switchMap(songNameLiveData) {
        OperationLiveData<ResultState<PagedList<Entity.Song>>> {
            if (tempDisposable?.isDisposed != true)
                tempDisposable?.dispose()
            tempDisposable = getSongsFromAlbumUseCase.getSongs(trackId).subscribe { resultState ->
                postValue((resultState))
            }
            tempDisposable?.track()
        }
    }

    fun getAlbumSongs(trackId: Int) {
        this.trackId = trackId
        songNameLiveData.postValue("")
    }

    fun getCurrentSong(trackId: Int) {
        if (tempDisposable?.isDisposed != true)
            tempDisposable?.dispose()
        tempDisposable = getSongFromTrackIdUseCase.getSong(trackId).subscribe { resultState ->
            currentSongLiveData.postValue(resultState)
        }
        tempDisposable?.track()
    }
}