package com.reactions.deathlines.presentation.ui.albumdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.reactions.deathlines.presentation.databinding.FragmentAlbumDetailBinding
import com.reactions.deathlines.presentation.ui.base.BaseFragment


class AlbumDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentAlbumDetailBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        val bindingUnmutable = binding!!
        return bindingUnmutable.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentTrackId = arguments?.get("trackId").toString()
        binding.btnPurchase.setOnClickListener(::onPurchaseBtnClicked)
        binding.btnDone.setOnClickListener(::onDoneBtnClicked)
        Log.d(tag, "onViewCreated currentTrackId: $currentTrackId")
    }

    fun onDoneBtnClicked(view: View) {
        view.findNavController().navigate(AlbumDetailFragmentDirections.actionAlbumDetailFragmentToHomeFragment())
    }

    fun onPurchaseBtnClicked(view: View) {
        val iTunesPurchaseUrl = "https://www.apple.com/itunes/"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(iTunesPurchaseUrl)
        startActivity(intent)
    }


}