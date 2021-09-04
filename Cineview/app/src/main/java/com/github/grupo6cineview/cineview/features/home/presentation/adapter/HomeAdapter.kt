package com.github.grupo6cineview.cineview.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import com.github.grupo6cineview.cineview.databinding.MovieItemBinding
import com.github.grupo6cineview.cineview.features.home.data.model.TrendingResult

class HomeAdapter(
    private val onClick: (id: Int, mediaType: String) -> Unit
) : PagingDataAdapter<TrendingResult, HomeViewHolder>(TrendingResult.DIFF_CALBACK_TRENDING) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).let { binding ->
            HomeViewHolder(binding)
        }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) = holder.bind(getItem(position), onClick)

}