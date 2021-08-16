package com.github.grupo6cineview.cineview.features.movie

import androidx.recyclerview.widget.RecyclerView
import com.github.grupo6cineview.cineview.databinding.CastItemBinding

class CastViewHolder(private val binding: CastItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cast: CastModel) {

        with(binding) {
            ivCastItemImage.setImageResource(cast.profileImageId)
            tvCastItemName.text = cast.name
            tvCastItemCharacter.text = cast.character
        }

    }

}