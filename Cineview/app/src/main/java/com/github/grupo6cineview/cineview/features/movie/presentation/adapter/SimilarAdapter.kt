package com.github.grupo6cineview.cineview.features.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.databinding.SimilarMovieItemBinding
import com.github.grupo6cineview.cineview.features.movie.data.model.similar.SimilarItem
import com.github.grupo6cineview.cineview.features.movie.data.model.similar.SimilarItem.Companion.DIFF_SIMILAR

class SimilarAdapter(
    private val onClick: (id: Int) -> Unit
) : ListAdapter<SimilarItem, SimilarAdapter.SimilarViewHolder>(DIFF_SIMILAR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder =
        LayoutInflater.from(parent.context).let { inflater ->
            SimilarMovieItemBinding.inflate(inflater, parent, false).let { binding ->
                SimilarViewHolder(binding)
            }
        }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(
                item,
                item == currentList.last(),
                onClick
            )
        }
    }

    inner class SimilarViewHolder(private val binding: SimilarMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            similarItem: SimilarItem,
            lastItem: Boolean,
            onClick: (id: Int) -> Unit
        ) {
            with(binding) {
                with(similarItem) {
                    Glide.with(itemView.context)
                        .load(poster)
                        .centerCrop()
                        .into(ivSimilarItemPoster)

                    tvSimilarItemTitle.text = title
                    tvSimilarItemReleaseYear.text = releaseYear
                    tvSimilarItemGenres.text = genres
                    if (lastItem) divider.visibility = GONE

                    clSimilarMovies.setOnClickListener { onClick(similarId) }
                }
            }
        }
    }
}