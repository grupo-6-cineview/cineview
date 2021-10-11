package com.github.grupo6cineview.cineview.features.movie.details.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.grupo6cineview.cineview.databinding.DetailsItemBinding
import com.github.grupo6cineview.cineview.features.movie.details.data.model.DetailsModel

class DetailsAdapter(private val detailsList: List<DetailsModel>) : RecyclerView.Adapter<DetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        DetailsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).let { binding ->
            return DetailsViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(detailsList[position], position == (detailsList.size - 1))
    }

    override fun getItemCount(): Int = detailsList.size
}