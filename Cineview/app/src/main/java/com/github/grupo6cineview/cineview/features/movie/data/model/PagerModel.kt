package com.github.grupo6cineview.cineview.features.movie.data.model

import androidx.recyclerview.widget.DiffUtil
import com.github.grupo6cineview.cineview.features.movie.data.model.cast.CastItem
import com.github.grupo6cineview.cineview.features.movie.data.model.details.DetailsItem
import com.github.grupo6cineview.cineview.features.movie.data.model.similar.SimilarItem
import com.github.grupo6cineview.cineview.features.movie.presentation.adapter.CastAdapter
import com.github.grupo6cineview.cineview.features.movie.presentation.adapter.DetailsAdapter
import com.github.grupo6cineview.cineview.features.movie.presentation.adapter.SimilarAdapter

data class PagerModel(
    val id: Int,
    val detailsAdapter: DetailsAdapter? = null,
    var detailsList: List<DetailsItem>? = null,
    val castAdapter: CastAdapter? = null,
    var castList: List<CastItem>? = null,
    val similarAdapter: SimilarAdapter? = null,
    var similarList: List<SimilarItem>? = null
) {

    companion object {
        val DIFF_PAGER = object : DiffUtil.ItemCallback<PagerModel>() {
            override fun areItemsTheSame(
                oldItem: PagerModel,
                newItem: PagerModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: PagerModel,
                newItem: PagerModel
            ): Boolean = oldItem.id == newItem.id
        }
    }
}