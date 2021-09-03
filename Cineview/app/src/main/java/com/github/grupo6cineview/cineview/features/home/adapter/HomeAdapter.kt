package com.github.grupo6cineview.cineview.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.grupo6cineview.cineview.databinding.MovieItemBinding
import com.github.grupo6cineview.cineview.datamodel.SearchTrendingResult

class HomeAdapter(private val onClick: () -> Unit) : ListAdapter<SearchTrendingResult, HomeViewHolder>(SearchTrendingResult.DIFF_CALBACK_SEARCH_TRENDING) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).let { binding ->
            HomeViewHolder(binding)
        }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) = holder.bind(getItem(position), onClick)

}