package com.reactions.deathlines.presentation.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.reactions.deathlines.domain.common.ResultState
import com.reactions.deathlines.domain.entity.Entity
import com.reactions.deathlines.presentation.common.extension.applyIoScheduler
import com.reactions.deathlines.presentation.common.extension.observe
import com.reactions.deathlines.presentation.databinding.FragmentHomeBinding
import com.reactions.deathlines.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, SongListAdapter.SongClickedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var isLoading = false

    private val adapter: SongListAdapter by lazy {
        SongListAdapter(this)
    }

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun onSongsLoaded(resultState: ResultState<PagedList<Entity.Song>>) {
        Log.d(tag, "onSongsLoaded resultState: $resultState")
        when (resultState) {
            is ResultState.Success -> {
                hideLoading()
                adapter.submitList(resultState.data)
            }
            is ResultState.Error -> {
                hideLoading()
                Toast.makeText(activity, resultState.throwable.message, Toast.LENGTH_SHORT).show()
                adapter.submitList(resultState.data)
            }
            is ResultState.Loading -> {
                adapter.submitList(resultState.data)
            }
        }
        isLoading = false
        fragmentHomeSwp.isRefreshing = false
    }

    @SuppressLint("CheckResult")
    private fun initView() {
//        fragmentHomeSwp.isRefreshing = true
        fragmentHomeSwp.setOnRefreshListener(this)
        fragmentHomeRcyMain.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        fragmentHomeRcyMain.setHasFixedSize(true)
        fragmentHomeRcyMain.adapter = adapter

        adapter.albumItemClickEvent.applyIoScheduler().subscribe { it ->
            Log.d(tag, "itemclickevent"            )
        }

//        showLoading()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        binding.songSearch.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    Log.d(tag, "onQueryTextSubmit: $query")
                    viewModel.getSongs(query)
                }
                return true
            }
        })
        observe(viewModel.albumsLiveData, ::onSongsLoaded)
    }

    override fun onRefresh() {
    }

    override fun onSongClicked(song: Entity.Song) {
        view?.findNavController()?.navigate(HomeFragmentDirections.navigateToAlbumDetailFragment(song.trackId.toString()))
    }
}