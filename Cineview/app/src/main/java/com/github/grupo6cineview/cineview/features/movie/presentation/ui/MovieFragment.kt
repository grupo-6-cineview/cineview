package com.github.grupo6cineview.cineview.features.movie.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.FragmentMovieBinding
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Api.PATH_TRENDING_MOVIE
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Api.PATH_TRENDING_TV
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Home.BUNDLE_KEY_ID
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Home.BUNDLE_KEY_MEDIA_TYPE
import com.github.grupo6cineview.cineview.features.movie.presentation.adapter.MoreContentAdapter
import com.github.grupo6cineview.cineview.features.movie.presentation.viewmodel.MovieViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator

class MovieFragment : BottomSheetDialogFragment() {

    private var binding: FragmentMovieBinding? = null
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.command = MutableLiveData()

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

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.run {
            getString(BUNDLE_KEY_MEDIA_TYPE, "").let { mediaType ->
                if (mediaType != "") {
                    getInt(BUNDLE_KEY_ID, 0).let { id ->
                        if (id != 0) {
                            when (mediaType) {
                                PATH_TRENDING_MOVIE -> viewModel.getMovieDetails(id)

                                PATH_TRENDING_TV -> viewModel.getTvDetails(id)

                                else -> { Log.d("api", "Else - MovieFrag Line 70") }
                            }
                        }
                    }
                }
            }
        }

        setupObservables()
    }

    private fun setupObservables() {
        viewModel.onSuccessMovieDetails.observe(viewLifecycleOwner) { movieDetails ->
            binding?.run {
                context?.let { contextNonNull ->
                    Glide.with(contextNonNull)
                        .load(movieDetails.backdrop_path)
                        .placeholder(R.drawable.no_backdrop_path)
                        .into(ivMovieFragBackdrop)
                }

                tvMovieFragTitle.text = movieDetails.title
                tvMovieFragRateCount.text = movieDetails.vote_average.toString()
                tvMovieFragOverview.text = movieDetails.overview
                tvMovieFragReleaseDate.text = movieDetails.release_date
                tvMovieFragRuntimeDuration.text = "${movieDetails.runtime ?: "-"} min"
            }
        }

        viewModel.onSuccessTvDetails.observe(viewLifecycleOwner) { tvDetails ->
            binding?.run {
                context?.let { contextNonNull ->
                    Glide.with(contextNonNull)
                        .load(tvDetails.backdrop_path)
                        .placeholder(R.drawable.no_backdrop_path)
                        .into(ivMovieFragBackdrop)
                }

                tvMovieFragTitle.text = tvDetails.name
                tvMovieFragRateCount.text = tvDetails.vote_average.toString()
                tvMovieFragOverview.text = tvDetails.overview
                tvMovieFragReleaseDate.text = tvDetails.first_air_date
                tvMovieFragRuntimeDuration.text = "- min"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}