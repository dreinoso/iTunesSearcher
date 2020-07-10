package com.reactions.deathlines.presentation.ui.albumdetail

import android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.paging.PagedList
import com.reactions.deathlines.domain.common.ResultState
import com.reactions.deathlines.domain.entity.Entity
import com.reactions.deathlines.presentation.common.extension.observe
import com.reactions.deathlines.presentation.databinding.FragmentAlbumDetailBinding
import com.reactions.deathlines.presentation.ui.base.BaseFragment
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import javax.inject.Inject

class AlbumDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentAlbumDetailBinding
    private var trackId: Int = 0

    private val viewModel: AlbumDetailsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(AlbumDetailsViewModel::class.java)
    }

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
        trackId = arguments?.get("trackId").toString().toInt()
        binding.btnPurchase.setOnClickListener(::onPurchaseBtnClicked)
        binding.btnDone.setOnClickListener(::onDoneBtnClicked)
        Log.d(tag, "onViewCreated currentTrackId: $trackId")
        observe(viewModel.albumSongsLiveData, ::onAlbumSongsLoaded)
        viewModel.getAlbumSongs(trackId)
    }

    fun onAlbumSongsLoaded(resultState: ResultState<PagedList<Entity.Song>>) {
        Log.d(tag, "onAlbumSongsLoaded ${resultState}")
        when (resultState) {
            is ResultState.Success -> {
                hideLoading()
                populateScreen(resultState.data)

            }
            is ResultState.Error -> {
                hideLoading()
                Toast.makeText(activity, resultState.throwable.message, Toast.LENGTH_SHORT).show()
            }
            is ResultState.Loading -> {
                Log.d(tag, "onAlbumSongsLoaded Loading ${resultState.data}")
            }
        }
    }

    private fun populateScreen(data: PagedList<Entity.Song>) {
        val songs = data.toList()
        binding.tvArtistName.text = songs[0].artistName
        binding.tvAlbumName.text = songs[0].collectionName
        val songNames = songs.map { it.trackName }
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                context,
                R.layout.simple_list_item_1,
                songNames)
        binding.lvAlbumSongs.setAdapter(arrayAdapter)
        val builder = Picasso.Builder(context!!)
        builder.downloader(OkHttp3Downloader(context))
        builder.build().load(songs[0].artworkUrl100)
                .error(com.reactions.deathlines.presentation.R.drawable.ic_launcher_background)
                .into(binding.ivAlbumIcon)
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