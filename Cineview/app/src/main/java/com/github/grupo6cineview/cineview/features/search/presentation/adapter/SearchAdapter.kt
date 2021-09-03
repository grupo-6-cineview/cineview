package com.github.grupo6cineview.cineview.features.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.grupo6cineview.cineview.databinding.MovieItemBinding
import com.github.grupo6cineview.cineview.features.search.data.model.SearchResult

class SearchAdapter(
    private val onClick: (id: Int, mediaType: String) -> Unit
) : ListAdapter<SearchResult, SearchViewHolder>(SearchResult.DIFF_CALBACK_SEARCH) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).let { binding ->
            SearchViewHolder(binding)
        }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) = holder.bind(getItem(position), onClick)

}