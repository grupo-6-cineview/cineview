package com.github.grupo6cineview.cineview.features.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.grupo6cineview.cineview.databinding.DetailsItemBinding
import com.github.grupo6cineview.cineview.features.movie.data.model.details.DetailsItem
import com.github.grupo6cineview.cineview.features.movie.data.model.details.DetailsItem.Companion.DIFF_DETAILS

class DetailsAdapter : ListAdapter<DetailsItem, DetailsAdapter.DetailsViewHolder>(DIFF_DETAILS) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder =
        LayoutInflater.from(parent.context).let { inflater ->
            DetailsItemBinding.inflate(inflater, parent, false).let { binding ->
                DetailsViewHolder(binding)
            }
        }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }

    inner class DetailsViewHolder(private val binding: DetailsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(detailsItem: DetailsItem) {
            with(binding) {
                tvDetailsLayoutTitle.text = detailsItem.title
                tvDetailsLayoutSubtitle.text = detailsItem.subtitle
            }
        }
    }
}