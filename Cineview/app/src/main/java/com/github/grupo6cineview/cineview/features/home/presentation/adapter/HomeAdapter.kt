package com.github.grupo6cineview.cineview.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.github.grupo6cineview.cineview.databinding.MovieItemHomeBinding
import com.github.grupo6cineview.cineview.features.home.data.model.HomeViewParams
import com.github.grupo6cineview.cineview.features.home.data.model.HomeViewParams.Companion.HOME_RESULT_DIFF

class HomeAdapter(
    private val onClick: (id: Int) -> Unit
) : PagingDataAdapter<HomeViewParams, HomeViewHolder>(HOME_RESULT_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        LayoutInflater.from(parent.context).let { inflater ->
            MovieItemHomeBinding.inflate(inflater, parent, false).let { binding ->
                HomeViewHolder(binding)
            }
        }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        getItem(position)?.let { homeViewParams ->
            holder.bind(homeViewParams, onClick)
        }
    }
}