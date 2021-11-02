package com.github.grupo6cineview.cineview.features.home.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.FragmentHomeBinding
import com.github.grupo6cineview.cineview.databinding.MovieItemCarouselBinding
import com.github.grupo6cineview.cineview.extension.appIsConnected
import com.github.grupo6cineview.cineview.extension.isError
import com.github.grupo6cineview.cineview.extension.isLoading
import com.github.grupo6cineview.cineview.extension.setVisible
import com.github.grupo6cineview.cineview.features.home.data.model.HomeViewParams
import com.github.grupo6cineview.cineview.features.home.domain.HomeIntent
import com.github.grupo6cineview.cineview.features.home.presentation.adapter.HomeAdapter
import com.github.grupo6cineview.cineview.features.home.presentation.adapter.HomeAdapterDatabase
import com.github.grupo6cineview.cineview.features.home.presentation.viewmodel.HomeViewModel
import com.github.grupo6cineview.cineview.features.movie.presentation.ui.MovieFragment
import com.github.grupo6cineview.cineview.utils.Command
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Detail.BUNDLE_KEY_HOME_INTENT
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Detail.BUNDLE_KEY_LOAD_DATABASE
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Detail.BUNDLE_KEY_MOVIE_ID
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Detail.TAG_SHOW_DETAIL_FRAGMENT
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModel()
    private val linearLayout: LinearLayoutManager get() = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    private val movieFragment: MovieFragment get() = MovieFragment { id -> onCLickMovie(id) }
    private fun getAdapter(intent: HomeIntent) = HomeAdapter(intent) { id, homeIntent -> onCLickMovie(id, homeIntent) }
    private fun getAdapterDatabase(intent: HomeIntent) = HomeAdapterDatabase(intent) { id, homeIntent -> onCLickMovie(id, homeIntent) }

    private fun onCLickMovie(
        id: Int,
        intent: HomeIntent? = null
    ) {
        Bundle().let { bundle ->
            bundle.putInt(BUNDLE_KEY_MOVIE_ID, id)
            bundle.putBoolean(BUNDLE_KEY_LOAD_DATABASE, !requireContext().appIsConnected())
            intent?.let { bundle.putString(BUNDLE_KEY_HOME_INTENT, it.name) }

            movieFragment.apply {
                arguments = bundle
                show(this@HomeFragment.parentFragmentManager, TAG_SHOW_DETAIL_FRAGMENT)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.command = MutableLiveData()
        setupAllLayoutManager()
        callMovies(context?.appIsConnected() ?: true)
        setupObservables()
        setupRefresh()
    }

    private fun setupAllLayoutManager() = binding?.run {
        rvHomeFragNowPlaying.layoutManager = linearLayout
        rvHomeFragTopRated.layoutManager = linearLayout
        rvHomeFragPopular.layoutManager = linearLayout
        rvHomeFragTrending.layoutManager = linearLayout
    }

    private fun callMovies(isConnected: Boolean) {
        if (isConnected) {
            resetDatabase()
            setupAllAdapters(database = false)
            callMoviesFromApi()
            setupAllStates()
        } else {
            setupAllAdapters(database = true)
            callMoviesFromDatabase()
        }
    }

    private fun setupAllAdapters(database: Boolean) =
        binding?.run {
            if (database) {
                rvHomeFragNowPlaying.adapter = getAdapterDatabase(HomeIntent.NowPlaying)
                rvHomeFragTopRated.adapter = getAdapterDatabase(HomeIntent.TopRated)
                rvHomeFragPopular.adapter = getAdapterDatabase(HomeIntent.Popular)
                rvHomeFragTrending.adapter = getAdapterDatabase(HomeIntent.Trending)
            } else {
                rvHomeFragNowPlaying.adapter = getAdapter(HomeIntent.NowPlaying)
                rvHomeFragTopRated.adapter = getAdapter(HomeIntent.TopRated)
                rvHomeFragPopular.adapter = getAdapter(HomeIntent.Popular)
                rvHomeFragTrending.adapter = getAdapter(HomeIntent.Trending)
            }
        }

    private fun callMoviesFromDatabase() = with(viewModel) {
        binding?.loadingLayout?.root?.visibility = VISIBLE
        getMoviesFromDatabase(HomeIntent.Carousel)
        getMoviesFromDatabase(HomeIntent.NowPlaying)
        getMoviesFromDatabase(HomeIntent.TopRated)
        getMoviesFromDatabase(HomeIntent.Popular)
        getMoviesFromDatabase(HomeIntent.Trending)
    }

    private fun resetDatabase() = viewModel.resetDatabase()

    private fun callMoviesFromApi() = binding?.run {
        callMoviesCarousel()
        getResultsFromPaging(recycler = rvHomeFragNowPlaying, intent = HomeIntent.NowPlaying)
        getResultsFromPaging(recycler = rvHomeFragTopRated, intent = HomeIntent.TopRated)
        getResultsFromPaging(recycler = rvHomeFragPopular, intent = HomeIntent.Popular)
        getResultsFromPaging(recycler = rvHomeFragTrending, intent = HomeIntent.Trending)
    }

    private fun callMoviesCarousel() {
        viewModel.getNowPlayingMovies()
    }

    private fun getResultsFromPaging(
        recycler: RecyclerView,
        intent: HomeIntent
    ) {
        lifecycleScope.launch {
            viewModel.getMovies(intent).collectLatest { pagingData ->
                recycler.adapter?.let { adapter ->
                    (adapter as? HomeAdapter)?.submitData(pagingData)
                }
            }
        }
    }

    private fun setupAllStates() {
        binding?.run {
            setupState(rvHomeFragNowPlaying)
            setupState(rvHomeFragTopRated)
            setupState(rvHomeFragPopular)
            setupState(rvHomeFragTrending)
        }
    }

    private fun setupState(recycler: RecyclerView) {
        lifecycleScope.launch {
            recycler.adapter?.let { adapter ->
                (adapter as? HomeAdapter)?.loadStateFlow?.collect { loadState ->
                    binding?.loadingLayout?.root?.visibility = if (loadState.isLoading()) VISIBLE else GONE
                    binding?.errorLayout?.root?.visibility = if (loadState.isError()) VISIBLE else GONE
                }
            }
        }
    }

    private fun setupObservables() = with(viewModel) {
        command.observe(viewLifecycleOwner) { command ->
            when (command) {
                is Command.Loading -> {
                    binding?.run {
                        loadingLayout.root.visibility =
                            if (command.value) {
                                errorLayout.root.setVisible(visible = false)
                                VISIBLE
                            } else GONE
                    }
                }
                is Command.Error -> binding?.errorLayout?.root?.setVisible(visible = true)
            }
        }

        onSuccessCarousel.observe(viewLifecycleOwner) { listCarousel ->
            binding?.run {
                with(carouselView) {
                    setViewListener { position ->
                        MovieItemCarouselBinding.inflate(layoutInflater).let { carouselBinding ->
                            context?.let { contextNonNull ->
                                Glide.with(contextNonNull)
                                    .load(listCarousel[position].backdropPath)
                                    .centerCrop()
                                    .placeholder(R.drawable.no_backdrop_path)
                                    .into(carouselBinding.ivCarouselBackdrop)
                            }

                            carouselBinding.tvCarouselTitle.text = listCarousel[position].title
                            carouselBinding.root
                        }
                    }

                    pageCount = listCarousel.size
                    setImageClickListener {
                        onCLickMovie(
                            listCarousel[currentItem].id,
                            HomeIntent.Carousel
                        )
                    }
                }
            }
        }

        onSuccessNowPlaying.observe(viewLifecycleOwner) { listNowPlaying ->
            binding?.run {
                updateDatabaseAdapter(
                    recycler = rvHomeFragNowPlaying,
                    list = listNowPlaying
                )
            }
        }

        onSuccessTopRated.observe(viewLifecycleOwner) { listTopRated ->
            binding?.run {
                updateDatabaseAdapter(
                    recycler = rvHomeFragTopRated,
                    list = listTopRated
                )
            }
        }

        onSuccessPopular.observe(viewLifecycleOwner) { listPopular ->
            binding?.run {
                updateDatabaseAdapter(
                    recycler = rvHomeFragPopular,
                    list = listPopular
                )
            }
        }

        onSuccessTrending.observe(viewLifecycleOwner) { listTrending ->
            binding?.run {
                updateDatabaseAdapter(
                    recycler = rvHomeFragTrending,
                    list = listTrending
                )
            }
        }
    }

    private fun updateDatabaseAdapter(
        recycler: RecyclerView,
        list: List<HomeViewParams>
    ) = binding?.run {
        if (list.isEmpty()) {
            errorLayout.root.setVisible(visible = true)
        } else {
            loadingLayout.root.setVisible(visible = false)
            recycler.apply {
                (adapter as? HomeAdapterDatabase)?.submitList(list)
            }
        }
    }

    private fun setupRefresh() {
        binding?.run {
            errorLayout.btRefresh.setOnClickListener {
                callMovies(context?.appIsConnected() ?: true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}