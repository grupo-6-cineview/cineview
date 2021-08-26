package com.github.grupo6cineview.cineview.features.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.grupo6cineview.cineview.databinding.MovieItemBinding
import com.github.grupo6cineview.cineview.features.search.data.model.recyclerview.MovieModel

class MovieAdapter(private val movieList: List<MovieModel>) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).let { binding ->
            MovieViewHolder(binding)
        }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(movieList[position])

    override fun getItemCount(): Int = movieList.size

}