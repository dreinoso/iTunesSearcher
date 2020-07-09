package com.reactions.deathlines.domain.usecase.album

import com.reactions.deathlines.domain.common.ResultState
import com.reactions.deathlines.domain.entity.Entity
import com.reactions.deathlines.domain.usecase.BaseUseCase
import io.reactivex.Single

interface GetSongFromTrackIdUseCase : BaseUseCase {

    fun getSong(trackId: Int): Single<ResultState<Entity.Song>>

}