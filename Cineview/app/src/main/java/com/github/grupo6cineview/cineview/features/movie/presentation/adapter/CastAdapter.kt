package com.github.grupo6cineview.cineview.features.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.databinding.CastItemBinding
import com.github.grupo6cineview.cineview.features.movie.data.model.cast.CastItem
import com.github.grupo6cineview.cineview.features.movie.data.model.cast.CastItem.Companion.DIFF_CAST

class CastAdapter : ListAdapter<CastItem, CastAdapter.CastViewHolder>(DIFF_CAST) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
        LayoutInflater.from(parent.context).let { inflater ->
            CastItemBinding.inflate(inflater, parent, false).let { binding ->
                CastViewHolder(binding)
            }
        }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }

    inner class CastViewHolder(private val binding: CastItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(castItem: CastItem) {
            with(binding) {
                with(castItem) {
                    Glide.with(itemView.context)
                        .load(poster)
                        .into(ivCastItemImage)

                    ivCastItemImage.clipToOutline = true
                    tvCastItemName.text = name
                    tvCastItemCharacter.text = character
                }
            }
        }
    }
}