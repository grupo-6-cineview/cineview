package com.github.grupo6cineview.cineview.features.home.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.databinding.FragmentHomeBinding
import com.github.grupo6cineview.cineview.databinding.MovieItemCarouselBinding
import com.github.grupo6cineview.cineview.extension.isError
import com.github.grupo6cineview.cineview.extension.isLoading
import com.github.grupo6cineview.cineview.extensions.Command
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Detail.BUNDLE_KEY_ID
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Detail.TAG_SHOW_DETAIL_FRAGMENT
import com.github.grupo6cineview.cineview.features.home.domain.HomeIntent
import com.github.grupo6cineview.cineview.features.home.presentation.adapter.HomeAdapter
import com.github.grupo6cineview.cineview.features.home.presentation.viewmodel.HomeViewModel
import com.github.grupo6cineview.cineview.features.movie.presentation.ui.MovieFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel
    private val linearLayout: LinearLayoutManager get() = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    private val adapter: HomeAdapter get() = HomeAdapter { id -> onCLickMovie(id) }
    private val movieFragment: MovieFragment get() = MovieFragment()
    private fun onCLickMovie(id: Int) {
        Bundle().let { bundle ->
            bundle.putInt(BUNDLE_KEY_ID, id)

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

        setupRecyclers()

        return binding?.root
    }

    private fun setupRecyclers() {
        binding?.run {
            rvHomeFragNowPlaying.layoutManager = linearLayout
            rvHomeFragNowPlaying.adapter = adapter

            rvHomeFragTopRated.layoutManager = linearLayout
            rvHomeFragTopRated.adapter = adapter

            rvHomeFragPopular.layoutManager = linearLayout
            rvHomeFragPopular.adapter = adapter

            rvHomeFragTrending.layoutManager = linearLayout
            rvHomeFragTrending.adapter = adapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            viewModel = ViewModelProvider(it)[HomeViewModel::class.java]
            viewModel.command = MutableLiveData()
        }

        callMovies()
        setupAllStates()
        setupObservables()
        setupRefresh()
    }

    private fun callMovies() {
        callMoviesCarousel()
        callNowPlaying()
        callTopRated()
        callPopular()
        callTrending()
    }

    private fun callMoviesCarousel() {
        viewModel.getNowPlayingMovies()
    }

    private fun callNowPlaying() {
        lifecycleScope.launch {
            viewModel.getMovies(HomeIntent.NowPlaying).collectLatest { pagingData ->
                binding?.run {
                    rvHomeFragNowPlaying.adapter?.let { adapter ->
                        (adapter as? HomeAdapter)?.submitData(pagingData)
                    }
                }
            }
        }
    }

    private fun callTopRated() {
        lifecycleScope.launch {
            viewModel.getMovies(HomeIntent.TopRated).collectLatest { pagingData ->
                binding?.run {
                    rvHomeFragTopRated.adapter?.let { adapter ->
                        (adapter as? HomeAdapter)?.submitData(pagingData)
                    }
                }
            }
        }
    }

    private fun callPopular() {
        lifecycleScope.launch {
            viewModel.getMovies(HomeIntent.Popular).collectLatest { pagingData ->
                binding?.run {
                    rvHomeFragPopular.adapter?.let { adapter ->
                        (adapter as? HomeAdapter)?.submitData(pagingData)
                    }
                }
            }
        }
    }

    private fun callTrending() {
        lifecycleScope.launch {
            viewModel.getMovies(HomeIntent.Trending).collectLatest { pagingData ->
                binding?.run {
                    rvHomeFragTrending.adapter?.let { adapter ->
                        (adapter as? HomeAdapter)?.submitData(pagingData)
                    }
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

    private fun setupObservables() {
        viewModel.command.observe(viewLifecycleOwner) { command ->
            when (command) {
                is Command.Loading -> {
                    binding?.run {
                        loadingLayout.root.visibility =
                            if (command.value) {
                                errorLayout.root.visibility = GONE
                                VISIBLE
                            } else GONE
                    }
                }
                is Command.Error -> binding?.errorLayout?.root?.visibility = VISIBLE
            }
        }

        viewModel.onSuccessNowPlaying.observe(viewLifecycleOwner) { listNowPlaying ->
            binding?.run {
                with(carouselView) {
                    setViewListener { position ->
                        MovieItemCarouselBinding.inflate(layoutInflater).let { carouselBinding ->
                            context?.let { contextNonNull ->
                                Glide.with(contextNonNull)
                                    .load(listNowPlaying[position].backdropPath)
                                    .centerCrop()
                                    .into(carouselBinding.ivCarouselBackdrop)
                            }

                            carouselBinding.tvCarouselTitle.text = listNowPlaying[position].title
                            carouselBinding.root
                        }
                    }

                    pageCount = listNowPlaying.size
                    setImageClickListener { onCLickMovie(listNowPlaying[currentItem].id) }
                }
            }
        }
    }

    private fun setupRefresh() {
        binding?.run {
            errorLayout.btRefresh.setOnClickListener {
                callMovies()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}