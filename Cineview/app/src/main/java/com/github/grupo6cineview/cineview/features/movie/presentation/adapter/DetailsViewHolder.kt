package com.github.grupo6cineview.cineview.features.movie.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.grupo6cineview.cineview.databinding.DetailsItemBinding
import com.github.grupo6cineview.cineview.features.movie.data.model.recyclerview.DetailsModel

class DetailsViewHolder(private val binding: DetailsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(details: DetailsModel, lastItem: Boolean) {
        with(binding) {
            tvDetailsLayoutTitle.text = details.title
            tvDetailsLayoutSubtitle.text = details.subtitle

            if (lastItem) {
                divider.visibility = View.GONE
            }
        }
    }

}