package com.github.grupo6cineview.cineview.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.github.grupo6cineview.cineview.databinding.MovieItemBinding
import com.github.grupo6cineview.cineview.features.home.data.model.HomeResult
import com.github.grupo6cineview.cineview.features.home.data.model.HomeResult.Companion.HOME_RESULT_DIFF

class HomeAdapter(
    private val onClick: (id: Int) -> Unit
) : PagingDataAdapter<HomeResult, HomeViewHolder>(HOME_RESULT_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        LayoutInflater.from(parent.context).let { inflater ->
            MovieItemBinding.inflate(inflater, parent, false).let { binding ->
                HomeViewHolder(binding)
            }
        }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        getItem(position)?.let { homeResult ->
            holder.bind(homeResult, onClick)
        }
    }
}