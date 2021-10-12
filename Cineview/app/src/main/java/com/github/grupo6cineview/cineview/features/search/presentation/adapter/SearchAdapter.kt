package com.github.grupo6cineview.cineview.features.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.github.grupo6cineview.cineview.databinding.MovieItemSearchBinding
import com.github.grupo6cineview.cineview.features.search.data.model.SearchResult
import com.github.grupo6cineview.cineview.features.search.data.model.SearchResult.Companion.DIFF_SEARCH

class SearchAdapter(
    private val onClick: (id: Int) -> Unit
) : PagingDataAdapter<SearchResult, SearchViewHolder>(DIFF_SEARCH) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        MovieItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false).let { binding ->
            SearchViewHolder(binding)
        }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { result ->
            holder.bind(result, onClick)
        }
    }
}