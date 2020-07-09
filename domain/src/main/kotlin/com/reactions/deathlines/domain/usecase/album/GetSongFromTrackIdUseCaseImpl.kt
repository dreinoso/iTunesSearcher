package com.reactions.deathlines.domain.usecase.album

import com.reactions.deathlines.domain.common.ResultState
import com.reactions.deathlines.domain.entity.Entity
import com.reactions.deathlines.domain.repository.album.SongsRepository
import io.reactivex.Single

class GetSongFromTrackIdUseCaseImpl(
        private val repository: SongsRepository) : GetSongFromTrackIdUseCase {

    override fun getSong(trackId: Int): Single<ResultState<Entity.Song>> =
            repository.getSongFromTrackId(trackId)
}