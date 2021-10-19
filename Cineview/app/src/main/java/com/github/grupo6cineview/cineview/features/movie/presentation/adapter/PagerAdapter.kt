package com.github.grupo6cineview.cineview.features.movie.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.DetailsListLayoutBinding
import com.github.grupo6cineview.cineview.databinding.SimilarMovieLayoutBinding
import com.github.grupo6cineview.cineview.features.movie.data.model.PagerModel
import com.github.grupo6cineview.cineview.features.movie.data.model.PagerModel.Companion.DIFF_PAGER

class PagerAdapter : ListAdapter<PagerModel, RecyclerView.ViewHolder>(DIFF_PAGER) {

    private fun getLayoutManager(context: Context, orientation: Int) = LinearLayoutManager(context, orientation, false)

    override fun getItemViewType(position: Int): Int =
        when (position) {
            FIRST_LAYOUT -> R.layout.details_list_layout
            else -> R.layout.similar_movie_layout
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.details_list_layout -> {
                LayoutInflater.from(parent.context).let { inflater ->
                    DetailsListLayoutBinding.inflate(inflater, parent, false).let { binding ->
                        DetailsViewHolder(binding)
                    }
                }
            }

            else -> {
                LayoutInflater.from(parent.context).let { inflater ->
                    SimilarMovieLayoutBinding.inflate(inflater, parent, false).let { binding ->
                        SimilarViewHolder(binding)
                    }
                }
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            when (holder) {
                is DetailsViewHolder -> {
                    holder.bind(item)
                }

                else -> {
                    (holder as? SimilarViewHolder)?.bind(item)
                }
            }
        }
    }

    inner class DetailsViewHolder(private val binding: DetailsListLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pagerModel: PagerModel) {
            with(pagerModel) {
                detailsAdapter?.let { dtAdapter ->
                    castAdapter?.let { ctAdapter ->
                        setupRecyclers(dtAdapter, ctAdapter)

                        dtAdapter.submitList(detailsList)
                        ctAdapter.submitList(castList)
                    }
                }
            }
        }

        private fun setupRecyclers(
            detailsAdapter: DetailsAdapter,
            castAdapter: CastAdapter
        ) {
            with(binding) {
                rvDetailsList.layoutManager = getLayoutManager(itemView.context, VERTICAL)
                rvDetailsList.adapter = detailsAdapter

                rvCastList.layoutManager = getLayoutManager(itemView.context, HORIZONTAL)
                rvCastList.adapter = castAdapter
            }
        }
    }

    inner class SimilarViewHolder(private val binding: SimilarMovieLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pagerModel: PagerModel) {
            with(pagerModel) {
                similarAdapter?.let { smAdapter ->
                    with(binding) {
                        rvSimilarMovies.layoutManager = getLayoutManager(itemView.context, VERTICAL)
                        rvSimilarMovies.adapter = smAdapter

                        smAdapter.submitList(similarList)
                    }
                }
            }
        }
    }

    companion object {
        const val FIRST_LAYOUT = 0
    }
}