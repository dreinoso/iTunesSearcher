package com.reactions.deathlines.presentation.ui.albumdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reactions.deathlines.presentation.R
import com.reactions.deathlines.presentation.ui.base.BaseFragment

class AlbumDetailFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_album_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentTrackId = arguments?.get("trackId").toString()
        Log.d(tag, "onViewCreated currentTrackId: $currentTrackId")
    }
}