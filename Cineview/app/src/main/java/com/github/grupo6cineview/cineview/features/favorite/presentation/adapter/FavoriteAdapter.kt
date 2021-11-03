package com.github.grupo6cineview.cineview.features.favorite.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.grupo6cineview.cineview.databinding.FavoriteItemBinding
import com.github.grupo6cineview.cineview.features.favorite.data.model.FavoriteViewParams
import com.github.grupo6cineview.cineview.features.favorite.data.model.FavoriteViewParams.Companion.FAVORITE_DIFF

class FavoriteAdapter(
    private val onClickFavorite: (id: Int) -> Unit,
    private val onClickMovie: (id: Int) -> Unit,
    private val scrollAction: () -> Unit
) : ListAdapter<FavoriteViewParams, FavoriteViewHolder>(FAVORITE_DIFF) {

    override fun onCurrentListChanged(
        previousList: MutableList<FavoriteViewParams>,
        currentList: MutableList<FavoriteViewParams>
    ) {
        super.onCurrentListChanged(previousList, currentList)
        scrollAction()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
        LayoutInflater.from(parent.context).let { inflater ->
            FavoriteItemBinding.inflate(inflater, parent, false).let { binding ->
                FavoriteViewHolder(binding)
            }
        }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(
                favorite = item,
                onClickFavorite = onClickFavorite,
                onClickMovie = onClickMovie
            )
        }
    }
}