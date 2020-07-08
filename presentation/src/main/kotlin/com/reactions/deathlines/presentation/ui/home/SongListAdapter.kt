package com.reactions.deathlines.presentation.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reactions.deathlines.domain.entity.Entity
import com.reactions.deathlines.presentation.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_song.view.*


class SongListAdapter : PagedListAdapter<Entity.Song, SongListAdapter.SongViewHolder>(SongDiffCallback()) {

    private val onAlbumItemClickSubject = PublishSubject.create<Entity.Song>()
    val albumItemClickEvent: Observable<Entity.Song> = onAlbumItemClickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val album = getItem(position)
        album?.let { holder.bind(album) }
    }

    inner class SongViewHolder(private var itemAlbumBinding: View) : RecyclerView.ViewHolder
    (itemAlbumBinding), View.OnClickListener {
        private val title = itemAlbumBinding.itemAlbumTxtTitle


        fun bind(songItem: Entity.Song) {
            title.text = songItem.trackName
            if (itemAlbumBinding!= null) {
//                itemAlbumBinding!!.entity = songItem
//                itemAlbumBinding!!.root.setOnClickListener(this)
//                itemAlbumBinding.productItemImvThumbnail.loadUrl(albumItem.image.url)
//                itemAlbumBinding!!.executePendingBindings()
            }
        }

        override fun onClick(view: View) {
            val album = getItem((adapterPosition))
            album?.let {
                val product: Entity.Song = album
                onAlbumItemClickSubject.onNext(product)
            }
        }
    }
}