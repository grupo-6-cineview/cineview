package com.github.grupo6cineview.cineview.features.favorite.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.github.grupo6cineview.cineview.databinding.FavoriteItemBinding
import com.github.grupo6cineview.cineview.features.favorite.data.model.recyclerview.FavoriteModel

class FavoriteViewHolder(val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(favorite: FavoriteModel) {
        binding.apply {
            with(favorite) {
                ivFavItemPoster.setImageResource(posteId)
                ivFavItemPoster.clipToOutline = true
                tvFavItemTitle.text = title
                tvFavItemOverview.text = overview
                tvFavItemRateCount.text = rateCount
                laFavItemFav.setMaxFrame(25)

                vFavItemClickFavButton.setOnClickListener {
                    with(laFavItemFav) {
                        if (frame == 25) {
                            speed = -3f
                        } else {
                            speed = 3f
                        }

                        playAnimation()
                    }
                }
            }
        }
    }

}