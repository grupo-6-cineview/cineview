package com.github.grupo6cineview.cineview.features.movie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.FragmentMovieBinding
import com.github.grupo6cineview.cineview.extension.appIsConnected
import com.github.grupo6cineview.cineview.extension.setVisible
import com.github.grupo6cineview.cineview.features.movie.data.model.PagerModel
import com.github.grupo6cineview.cineview.features.movie.data.model.viewparams.CastViewParams
import com.github.grupo6cineview.cineview.features.movie.data.model.viewparams.DetailsViewParams
import com.github.grupo6cineview.cineview.features.movie.data.model.viewparams.SimilarViewParams
import com.github.grupo6cineview.cineview.features.movie.presentation.adapter.CastAdapter
import com.github.grupo6cineview.cineview.features.movie.presentation.adapter.DetailsAdapter
import com.github.grupo6cineview.cineview.features.movie.presentation.adapter.PagerAdapter
import com.github.grupo6cineview.cineview.features.movie.presentation.adapter.SimilarAdapter
import com.github.grupo6cineview.cineview.features.movie.presentation.viewmodel.MovieViewModel
import com.github.grupo6cineview.cineview.utils.Command
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Detail.BUNDLE_KEY_HOME_INTENT
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Detail.BUNDLE_KEY_MOVIE_ID
import com.github.grupo6cineview.cineview.utils.GenresCache
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment(
    onClick: (id: Int) -> Unit
) : BottomSheetDialogFragment() {

    private var binding: FragmentMovieBinding? = null
    private val viewModel: MovieViewModel by viewModel()
    private val pagerAdapter by lazy { PagerAdapter() }
    private lateinit var movieDetails: DetailsViewParams
    private lateinit var movieCast: CastViewParams
    private lateinit var movieSimilar: SimilarViewParams

    private val movieId by lazy {
        arguments?.getInt(BUNDLE_KEY_MOVIE_ID, 0) ?: 0
    }

    private val homeIntent by lazy {
        arguments?.getString(BUNDLE_KEY_HOME_INTENT, "NowPlaying") ?: "NowPlaying"
    }

    private val pagerModelList: List<PagerModel> = listOf(
        PagerModel(
            id = 0,
            detailsAdapter = DetailsAdapter(),
            castAdapter = CastAdapter()
        ),
        PagerModel(
            id = 1,
            similarAdapter = SimilarAdapter(onClick)
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
        viewModel.command = MutableLiveData()
        setViews()
        setListeners()
        setupViewPager()
        setupObservables()
        verifyFavorite()
    }

    private fun setViews() =
        binding?.run {
            loadingLayout.background.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black_light
                )
            )

            errorLayout.background.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black_light
                )
            )

            errorLayoutDatabase.background.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black_light
                )
            )

            btMovieFragFavorite.setMaxFrame(MAX_FRAME_LIKE_ANIM)
        }

    private fun setListeners() = binding?.run {
        errorLayout.btRefresh.setOnClickListener {
            setError(visible = false)
            verifyFavorite()
        }

        errorLayoutDatabase.btRefresh.setOnClickListener {
            errorLayoutDatabase.root.setVisible(visible = false)
            getAllDetails()
        }

        btMovieFragClose.setOnClickListener { dismiss() }

        btMovieFragFavorite.setOnClickListener { view ->
            (view as? LottieAnimationView)?.apply {
                if (frame == MAX_FRAME_LIKE_ANIM) {
                    speed = -3f
                    playAnimation()

                    context?.run {
                        if (verifyInit() && appIsConnected())
                            viewModel.deleteFavorite(
                                movieDetails,
                                movieCast,
                                movieSimilar
                            )
                    }
                } else {
                    speed = 3f
                    playAnimation()

                    context?.run {
                        if (verifyInit() && appIsConnected())
                            viewModel.saveFavorite(
                                movieDetails,
                                movieCast,
                                movieSimilar
                            )
                    }
                }
            }
        }
    }

    private fun verifyInit() =
        ::movieDetails.isInitialized && ::movieCast.isInitialized && ::movieSimilar.isInitialized

    private fun verifyFavorite() = viewModel.verifyFavorite(movieId)

    private fun getFavoriteFromDb() {
        viewModel.getFavoriteWithCasts(movieId)
        viewModel.getFavoriteWithSimilars(movieId)
    }

    private fun loadMovieFromDatabase() =
        viewModel.getMovieFromDatabase(
            movieId = movieId,
            intent = homeIntent
        )

    private fun getAllDetails() {
        callDetails()
        callCast()
        callSimilar()
    }

    private fun callDetails() = viewModel.getMovieDetails(movieId)

    private fun callCast() = viewModel.getMovieCast(movieId)

    private fun callSimilar() = viewModel.getSimilarMovies(movieId)

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
            command.observe(viewLifecycleOwner) { command ->
                when (command) {
                    is Command.Loading -> binding?.run {
                        setLoading(visible = command.value)
                    }

                    is Command.Error -> context?.run {
                        if (!appIsConnected()) {
                            verifyFavorite()
                        } else {
                            setError(visible = true)
                        }
                    }
                }
            }

            onVerifyFavorite.observe(viewLifecycleOwner) { isFavorite ->
                binding?.btMovieFragFavorite?.frame =
                    if (isFavorite)
                        MAX_FRAME_LIKE_ANIM
                    else
                        MIN_FRAME_LIKE_ANIM

                context?.run {
                    if (appIsConnected()) {
                        if (GenresCache.genresIsNull) {
                            viewModel.getAllGenres()
                        }
                        getAllDetails()
                    } else {
                        if (isFavorite)
                            getFavoriteFromDb()
                        else
                            loadMovieFromDatabase()
                    }
                }
            }

            onSuccessGenres.observe(viewLifecycleOwner) { genresResponse ->
                with(GenresCache) {
                    if (genresIsNull) {
                        setGenresCached(genresResponse.genres)
                    }
                }
            }

            onSuccessDetails.observe(viewLifecycleOwner) { detailsViewParams ->
                binding?.run {
                    movieDetails = detailsViewParams

                    with(detailsViewParams) {
                        Glide.with(this@MovieFragment)
                            .load(backdrop)
                            .placeholder(R.drawable.no_backdrop_path)
                            .into(ivMovieFragBackdrop)

                        tvMovieFragTitle.text = title
                        tvMovieFragOverview.text = overview
                        tvMovieFragStars.text = voteAverage
                        tvMovieFragViews.text = voteCount

                        pagerModelList[0].detailsList = detailsList
                    }
                }
            }

            onSuccessCast.observe(viewLifecycleOwner) { castViewParams ->
                movieCast = castViewParams

                pagerModelList[0].castList = castViewParams.castItems
                submitListAdapter()
            }

            onSuccessSimilar.observe(viewLifecycleOwner) { similarViewParams ->
                movieSimilar = similarViewParams

                pagerModelList[1].similarList = similarViewParams.similarMovies
                submitListAdapter()
            }

            onSuccedLoadFromDatabase.observe(viewLifecycleOwner) { homeViewParams ->
                binding?.run {
                    homeViewParams?.run {
                        Glide.with(this@MovieFragment)
                            .load(backdropPath)
                            .placeholder(R.drawable.no_backdrop_path)
                            .into(ivMovieFragBackdrop)

                        tvMovieFragTitle.text = title
                        tvMovieFragOverview.text = overview
                        tvMovieFragStars.text = voteAverage
                        tvMovieFragViews.text = voteCount

                        errorLayoutDatabase.root.setVisible(visible = true)
                    } ?: kotlin.run {
                        setError(visible = true)
                    }
                }
            }
        }
    }

    private fun setLoading(visible: Boolean) = binding?.run {
        loadingLayout.root.setVisible(visible = visible)
        tlMovieFragMoreInfo.setVisible(visible = !visible)
        vpMovieFragMoreInfo.setVisible(visible = !visible)
    }

    private fun setError(visible: Boolean) = binding?.run {
        errorLayout.root.setVisible(visible = visible)
        tlMovieFragMoreInfo.setVisible(visible = !visible)
        vpMovieFragMoreInfo.setVisible(visible = !visible)
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
            binding?.run {
                errorLayoutDatabase.root.setVisible(visible = false)
            }
            pagerAdapter.submitList(pagerModelList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    companion object {
        private const val MAX_FRAME_LIKE_ANIM = 25
        private const val MIN_FRAME_LIKE_ANIM = 0
    }
}