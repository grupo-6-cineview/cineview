package com.github.grupo6cineview.cineview.features.favorite.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.FavoriteItemBinding
import com.github.grupo6cineview.cineview.extension.getOverviewFavoriteFormat
import com.github.grupo6cineview.cineview.features.favorite.data.model.FavoriteViewParams
import com.github.grupo6cineview.cineview.utils.ShareHelper

class FavoriteViewHolder(val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        favorite: FavoriteViewParams,
        onClickFavorite: (id: Int) -> Unit,
        onClickMovie: (id: Int) -> Unit
    ) {
        binding.apply {
            with(favorite) {
                Glide.with(itemView.context)
                    .load(posterPath)
                    .centerCrop()
                    .placeholder(R.drawable.no_poster_path)
                    .into(ivFavItemPoster)

                ivFavItemPoster.clipToOutline = true
                tvFavItemTitle.text = title
                tvFavItemOverview.text = overview.getOverviewFavoriteFormat()
                tvFavItemRateCount.text = voteAverage
                laFavItemFav.setMaxFrame(MAX_FRAME_LIKE_ANIM)
                laFavItemFav.frame = MAX_FRAME_LIKE_ANIM

                vFavItemClickFavButton.setOnClickListener {
                    with(laFavItemFav) {
                        if (frame == MAX_FRAME_LIKE_ANIM) {
                            speed = -3f
                            playAnimation()
                            onClickFavorite(movieId)
                        } else {
                            speed = 3f
                            playAnimation()
                        }
                    }
                }

                btFavItemShare.setOnClickListener {
                    ShareHelper.onClickShare(
                        context = itemView.context,
                        movie = title
                    )
                }

                favoriteContainer.setOnClickListener {
                    onClickMovie(movieId)
                }
            }
        }
    }

    companion object {
        private const val MAX_FRAME_LIKE_ANIM = 25
    }
}