package com.github.grupo6cineview.cineview.features.movie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.FragmentMovieBinding
import com.github.grupo6cineview.cineview.features.movie.presentation.adapter.MoreContentAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator

class MovieFragment : BottomSheetDialogFragment() {

    var binding: FragmentMovieBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(inflater, container, false)

        binding?.run {

            val adapter = MoreContentAdapter(this@MovieFragment, listOf(CastFragment(), DetailsFragment()))
            vpMovieFragMoreContent.adapter = adapter
            vpMovieFragMoreContent.isSaveEnabled = false

            TabLayoutMediator(tlMovieFragMoreInfo, vpMovieFragMoreContent) { tab, position ->

                when(position) {
                    0 -> tab.text = getString(R.string.tab_item_1_movie_frag)
                    1 -> tab.text = getString(R.string.tab_item_2_movie_frag)
                }

            }.attach()

        }

        setContents()

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    fun setContents() {
        binding?.run {
            backdropId?.let { ivMovieFragBackdrop.setImageResource(it) }
            tvMovieFragTitle.text = title
            tvMovieFragRateCount.text = rateCount
            tvMovieFragOverview.text = overView
            tvMovieFragReleaseDate.text = releaseDate
            tvMovieFragRuntimeDuration.text = runtimeDuration
        }
    }

    companion object {
        val newInstance = MovieFragment()

        var backdropId: Int? = null
        var title: String? = null
        var rateCount: String? = null
        var overView: String? = null
        var releaseDate: String? = null
        var runtimeDuration: String? = null
    }

}