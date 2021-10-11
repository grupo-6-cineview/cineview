package com.github.grupo6cineview.cineview.features.movie.cast.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.github.grupo6cineview.cineview.databinding.CastItemBinding
import com.github.grupo6cineview.cineview.features.movie.cast.data.model.CastModel

class CastViewHolder(private val binding: CastItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cast: CastModel) {

        with(binding) {
            ivCastItemImage.setImageResource(cast.profileImageId)
            ivCastItemImage.clipToOutline = true
            tvCastItemName.text = cast.name
            tvCastItemCharacter.text = cast.character
        }

    }

}