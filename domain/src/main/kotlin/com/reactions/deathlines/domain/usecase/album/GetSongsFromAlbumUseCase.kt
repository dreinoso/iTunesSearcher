package com.reactions.deathlines.domain.usecase.album

import androidx.paging.PagedList
import io.reactivex.Flowable
import com.reactions.deathlines.domain.common.ResultState
import com.reactions.deathlines.domain.entity.Entity
import com.reactions.deathlines.domain.usecase.BaseUseCase

interface GetSongsFromAlbumUseCase : BaseUseCase {

    fun getSongs(collectionId: Int): Flowable<ResultState<PagedList<Entity.Song>>>

}