package com.github.grupo6cineview.cineview.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.grupo6cineview.cineview.databinding.MovieItemHomeBinding
import com.github.grupo6cineview.cineview.features.home.data.model.HomeViewParams
import com.github.grupo6cineview.cineview.features.home.data.model.HomeViewParams.Companion.HOME_DIFF
import com.github.grupo6cineview.cineview.features.home.domain.HomeIntent

class HomeAdapterDatabase(
    private val intent: HomeIntent,
    private val onClick: (id: Int, intent: HomeIntent) -> Unit
) : ListAdapter<HomeViewParams, HomeViewHolder>(HOME_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        LayoutInflater.from(parent.context).let { inflater ->
            MovieItemHomeBinding.inflate(inflater, parent, false).let { binding ->
                HomeViewHolder(binding)
            }
        }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(
                movie = item,
                onClick = onClick,
                intent = intent
            )
        }
    }
}