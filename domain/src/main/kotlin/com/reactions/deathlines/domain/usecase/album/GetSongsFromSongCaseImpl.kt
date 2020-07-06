package com.reactions.deathlines.domain.usecase.album

import androidx.paging.PagedList
import io.reactivex.Flowable
import com.reactions.deathlines.domain.common.ResultState
import com.reactions.deathlines.domain.common.transformer.FTransformer
import com.reactions.deathlines.domain.common.transformer.STransformer
import com.reactions.deathlines.domain.entity.Entity
import com.reactions.deathlines.domain.repository.album.SongsRepository
import io.reactivex.Single

class GetSongsFromSongCaseImpl(
        private val repository: SongsRepository) : GetSongsFromSongUseCase {

    override fun getSongs(songName: String): Flowable<ResultState<PagedList<Entity.Song>>> =
            repository.getSongs(songName)
}