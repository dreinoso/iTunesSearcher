package com.reactions.deathlines.presentation.ui.albumdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import io.reactivex.disposables.Disposable
import com.reactions.deathlines.domain.common.ResultState
import com.reactions.deathlines.domain.entity.Entity
import com.reactions.deathlines.domain.usecase.album.GetSongsFromAlbumUseCase
import com.reactions.deathlines.presentation.common.OperationLiveData
import com.reactions.deathlines.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class AlbumDetailsViewModel @Inject constructor(
        private val getSongsFromAlbumUseCase: GetSongsFromAlbumUseCase) : BaseViewModel() {

    private var tempDisposable: Disposable? = null
    private val fetch = MutableLiveData<String>()
    private var trackId = 0

    val  albumSongsLiveData: LiveData<ResultState<PagedList<Entity.Song>>> = Transformations.switchMap(fetch) {
        OperationLiveData<ResultState<PagedList<Entity.Song>>> {
            if (tempDisposable?.isDisposed != true)
                tempDisposable?.dispose()
            tempDisposable = getSongsFromAlbumUseCase.getSongs(trackId).subscribe { resultState ->
                Log.d(this.javaClass.name, "resultState: $resultState")
                postValue(resultState)
            }
            tempDisposable?.track()
        }
    }

    fun getAlbumSongs(trackId: Int) {
        this.trackId = trackId
        fetch.postValue("")
    }
}