package com.reactions.deathlines.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import io.reactivex.disposables.Disposable
import com.reactions.deathlines.domain.common.ResultState
import com.reactions.deathlines.domain.entity.Entity
import com.reactions.deathlines.domain.usecase.album.GetSongsFromSongUseCase
import com.reactions.deathlines.presentation.common.OperationLiveData
import com.reactions.deathlines.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getSongsFromSongUseCase: GetSongsFromSongUseCase) : BaseViewModel() {

    private var tempDisposable: Disposable? = null
    private var songName =""
    private val fetch = MutableLiveData<String>()

    val albumsLiveData: LiveData<ResultState<PagedList<Entity.Song>>> = Transformations.switchMap(fetch) {
        OperationLiveData<ResultState<PagedList<Entity.Song>>> {
            if (tempDisposable?.isDisposed != true)
                tempDisposable?.dispose()
            tempDisposable = getSongsFromSongUseCase.getSongs(songName).subscribe { resultState ->
                postValue((resultState))
            }
            tempDisposable?.track()
        }
    }

    fun getSongs(songName: String) {
        this.songName = songName
        fetch.postValue("")
    }
}