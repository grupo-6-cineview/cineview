package com.github.grupo6cineview.cineview.features.movie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.databinding.FragmentMovieBinding
import com.github.grupo6cineview.cineview.extension.rateFormat
import com.github.grupo6cineview.cineview.extension.viewsFormat
import com.github.grupo6cineview.cineview.features.movie.data.model.PagerModel
import com.github.grupo6cineview.cineview.features.movie.presentation.adapter.CastAdapter
import com.github.grupo6cineview.cineview.features.movie.presentation.adapter.DetailsAdapter
import com.github.grupo6cineview.cineview.features.movie.presentation.adapter.PagerAdapter
import com.github.grupo6cineview.cineview.features.movie.presentation.adapter.SimilarAdapter
import com.github.grupo6cineview.cineview.features.movie.presentation.viewmodel.MovieViewModel
import com.github.grupo6cineview.cineview.utils.GenresCache
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator

class MovieFragment : BottomSheetDialogFragment() {

    private var binding: FragmentMovieBinding? = null
    private lateinit var viewModel: MovieViewModel
    private val pagerAdapter by lazy { PagerAdapter() }
    private val movieId by lazy {
        arguments?.getInt("MOVIE_ID", 0) ?: 0
    }
    private val pagerModelList: List<PagerModel> = listOf(
        PagerModel(
            id = 0,
            detailsAdapter = DetailsAdapter(),
            castAdapter = CastAdapter()
        ),
        PagerModel(
            id = 1,
            similarAdapter = SimilarAdapter()
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.command = MutableLiveData()
        setupViewPager()

        if (GenresCache.genresIsNull) {
            viewModel.getAllGenres()
        }
        viewModel.getMovieDetails(movieId)
        viewModel.getMovieCast(movieId)
        viewModel.getSimilarMovies(movieId)

        setupObservables()
    }

    private fun setupViewPager() {
        binding?.run {
            vpMovieFragMoreInfo.adapter = pagerAdapter
            TabLayoutMediator(tlMovieFragMoreInfo, vpMovieFragMoreInfo) { tab, position ->
                when (position) {
                    0 -> tab.text = "Mais Informações"
                    1 -> tab.text = "Filmes Relacionados"
                }
            }.attach()
            vpMovieFragMoreInfo.requestDisallowInterceptTouchEvent(false)
        }
    }

    private fun setupObservables() {
        with(viewModel) {
            onSuccessGenres.observe(viewLifecycleOwner) { genresResponse ->
                with(GenresCache) {
                    if (genresIsNull) {
                        setGenresCached(genresResponse.genres)
                    }
                }
            }

            onSuccessDetails.observe(viewLifecycleOwner) { detailResponse ->
                binding?.run {
                    with(detailResponse) {
                        Glide.with(this@MovieFragment)
                            .load(backdropPath)
                            .into(ivMovieFragBackdrop)

                        tvMovieFragTitle.text = title
                        tvMovieFragOverview.text = overview
                        tvMovieFragStars.text = voteAverage.rateFormat()
                        tvMovieFragViews.text = voteCount.viewsFormat()

                        pagerModelList[0].detailsList = detailResponse.detailsList
                    }
                }
            }

            onSuccessCast.observe(viewLifecycleOwner) { castList ->
                pagerModelList[0].castList = castList
                submitListAdapter()
            }

            onSuccessSimilar.observe(viewLifecycleOwner) { similarList ->
                pagerModelList[1].similarList = similarList
                submitListAdapter()
            }
        }
    }

    private fun submitListAdapter() {
        var canSubmit = true

        pagerModelList.forEachIndexed { i, pagerModel ->
            canSubmit = when (i) {
                0 -> pagerModel.castList != null
                1 -> {
                    if (canSubmit) {
                        pagerModel.similarList != null
                    } else false
                }
                else -> false
            }
        }

        if (canSubmit) {
            pagerAdapter.submitList(pagerModelList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}