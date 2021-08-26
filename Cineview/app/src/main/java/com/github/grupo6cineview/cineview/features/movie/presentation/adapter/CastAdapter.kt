package com.github.grupo6cineview.cineview.features.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.grupo6cineview.cineview.databinding.CastItemBinding
import com.github.grupo6cineview.cineview.features.movie.data.model.recyclerview.CastModel

class CastAdapter(private val castList: List<CastModel>) : RecyclerView.Adapter<CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        CastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).let { binding ->
            return CastViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(castList[position])
    }

    override fun getItemCount(): Int = castList.size
}