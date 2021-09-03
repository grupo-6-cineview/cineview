package com.github.grupo6cineview.cineview.features.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.grupo6cineview.cineview.databinding.MovieItemBinding
import com.github.grupo6cineview.cineview.datamodel.SearchTrendingResult

class SearchAdapter(
    private val onClick: () -> Unit
) : ListAdapter<SearchTrendingResult, SearchViewHolder>(SearchTrendingResult.DIFF_CALBACK_SEARCH_TRENDING) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).let { binding ->
            SearchViewHolder(binding)
        }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) = holder.bind(getItem(position), onClick)

}