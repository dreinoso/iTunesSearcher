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

class HomeViewModel @Inject constructor(private val getAlbumsUseCase: GetSongsFromSongUseCase) : BaseViewModel() {

    private var tempDispossable: Disposable? = null
    //var page = 1
    private val albumToBeDeleted = MutableLiveData<Entity.Song>()
    //private val pageLiveData by lazy { MutableLiveData<String>()/*.defaultValue(1)*/ }
    //val pageNumberLiveData by lazy { MutableLiveData<Int>().defaultValue(1) }
    private val fetch = MutableLiveData<String>()

    val deletedAlbumLiveData: LiveData<ResultState<Int>> = Transformations.switchMap(albumToBeDeleted) {
        OperationLiveData<ResultState<Int>> {
            getAlbumsUseCase.loadSongs(it).toFlowable().subscribe { resultState ->
                postValue(resultState)
            }
        }
    }

    fun deleteAlbum(album: Entity.Song) {
        albumToBeDeleted.postValue(album)
    }

    //val networkErrors: LiveData<String> = Transformations.switchMap(repoResult) { it -> it.networkErrors }

    val albumsLiveData: LiveData<ResultState<PagedList<Entity.Song>>> = Transformations.switchMap(fetch) {
        OperationLiveData<ResultState<PagedList<Entity.Song>>> {

            if (tempDispossable?.isDisposed != true)
            //if (tempDispossable != null && !tempDispossable!!.isDisposed)
                tempDispossable?.dispose()
            tempDispossable = getAlbumsUseCase.getSongs().subscribe { resultState ->
                postValue((resultState))
                /*when (resultState) {
                    is ResultState.Success ->
                        pageNumberLiveData.postValue(resultState.data.size)
                }*/
            }
            tempDispossable?.track()
        }
    }

    fun getSongs() {
        fetch.postValue("")
        //page++
    }
}