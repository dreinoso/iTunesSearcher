package com.reactions.deathlines.presentation.ui.home

import android.util.Log
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

    private var tempDispossable: Disposable? = null
    private var songName =""
    //var page = 1
    val songsLiveData = MutableLiveData<ResultState<PagedList<Entity.Song>>>()
    val albumSongsLiveData = MutableLiveData<PagedList<Entity.Song>>()
    //private val pageLiveData by lazy { MutableLiveData<String>()/*.defaultValue(1)*/ }
    //val pageNumberLiveData by lazy { MutableLiveData<Int>().defaultValue(1) }
    private val songNameLiveData = MutableLiveData<String>()

    //val networkErrors: LiveData<String> = Transformations.switchMap(repoResult) { it -> it.networkErrors }

    val albumsLiveData: LiveData<ResultState<PagedList<Entity.Song>>> = Transformations.switchMap(songNameLiveData) {
        OperationLiveData<ResultState<PagedList<Entity.Song>>> {

            if (tempDispossable?.isDisposed != true)
            //if (tempDispossable != null && !tempDispossable!!.isDisposed)
                tempDispossable?.dispose()
            tempDispossable = getSongsFromSongUseCase.getSongs(songName).subscribe { resultState ->
                postValue((resultState))
            }
            tempDispossable?.track()
        }
    }

    fun getSongs(songName: String) {
        this.songName = songName
        songNameLiveData.postValue("")
        //page++
    }
}