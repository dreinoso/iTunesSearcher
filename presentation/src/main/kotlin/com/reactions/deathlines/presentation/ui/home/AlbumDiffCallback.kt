package com.reactions.deathlines.presentation.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.reactions.deathlines.domain.entity.Entity

class AlbumDiffCallback : DiffUtil.ItemCallback<Entity.Song>() {

    override fun areItemsTheSame(oldItem: Entity.Song, newItem: Entity.Song): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Entity.Song, newItem: Entity.Song): Boolean =
            oldItem == newItem

    override fun getChangePayload(oldItem: Entity.Song, newItem: Entity.Song): Any? {
        // Return particular field for changed item.
        return super.getChangePayload(oldItem, newItem)
    }
}