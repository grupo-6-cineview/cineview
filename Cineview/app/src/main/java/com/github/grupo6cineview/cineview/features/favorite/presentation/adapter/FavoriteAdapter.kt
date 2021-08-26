package com.github.grupo6cineview.cineview.features.favorite.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.grupo6cineview.cineview.databinding.FavoriteItemBinding
import com.github.grupo6cineview.cineview.features.favorite.data.model.recyclerview.FavoriteModel

class FavoriteAdapter(
    private val favList: List<FavoriteModel>
) : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
        FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).let { binding ->
            FavoriteViewHolder(binding)
        }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) = holder.bind(favList[position])

    override fun getItemCount(): Int = favList.size
}