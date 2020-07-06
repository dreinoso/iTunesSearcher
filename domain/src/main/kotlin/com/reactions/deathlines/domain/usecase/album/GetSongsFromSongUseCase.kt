package com.reactions.deathlines.domain.usecase.album

import androidx.paging.PagedList
import io.reactivex.Flowable
import com.reactions.deathlines.domain.common.ResultState
import com.reactions.deathlines.domain.entity.Entity
import com.reactions.deathlines.domain.usecase.BaseUseCase

interface GetSongsFromSongUseCase : BaseUseCase {

    fun getSongs(songName: String): Flowable<ResultState<PagedList<Entity.Song>>>

}