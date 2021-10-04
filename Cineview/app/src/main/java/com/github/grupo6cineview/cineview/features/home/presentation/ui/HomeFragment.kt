package com.github.grupo6cineview.cineview.features.home.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.databinding.FragmentHomeBinding
import com.github.grupo6cineview.cineview.databinding.MovieItemCarouselBinding
import com.github.grupo6cineview.cineview.features.home.domain.HomeIntent
import com.github.grupo6cineview.cineview.features.home.presentation.adapter.HomeAdapter
import com.github.grupo6cineview.cineview.features.home.presentation.viewmodel.HomeViewModel
import com.github.grupo6cineview.cineview.features.movie.movie.presentation.ui.MovieFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel
    private val movieFragment: MovieFragment get() = MovieFragment()

    private val adapterNowPlaying by lazy {
        HomeAdapter { id ->
//            with(movieFragment) {
//                Bundle().run {
//                    putInt(BUNDLE_KEY_ID, id)
//
//                    arguments = this
//                }
//
//                show(this@HomeFragment.parentFragmentManager, "BOTTOM_SHEET_FRAG")
//            }
        }
    }

    private val adapterTopRated by lazy {
        HomeAdapter { id ->
//            with(movieFragment) {
//                Bundle().run {
//                    putInt(BUNDLE_KEY_ID, id)
//
//                    arguments = this
//                }
//
//                show(this@HomeFragment.parentFragmentManager, "BOTTOM_SHEET_FRAG")
//            }
        }
    }

    private val adapterPopular by lazy {
        HomeAdapter { id ->
//            with(movieFragment) {
//                Bundle().run {
//                    putInt(BUNDLE_KEY_ID, id)
//
//                    arguments = this
//                }
//
//                show(this@HomeFragment.parentFragmentManager, "BOTTOM_SHEET_FRAG")
//            }
        }
    }

    private val adapterUpcoming by lazy {
        HomeAdapter { id ->
//            with(movieFragment) {
//                Bundle().run {
//                    putInt(BUNDLE_KEY_ID, id)
//
//                    arguments = this
//                }
//
//                show(this@HomeFragment.parentFragmentManager, "BOTTOM_SHEET_FRAG")
//            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding?.run {

            context?.let { contextNonNull ->
                rvHomeFragNowPlaying.layoutManager = LinearLayoutManager(contextNonNull, RecyclerView.HORIZONTAL, false)
                rvHomeFragNowPlaying.adapter = adapterNowPlaying

                rvHomeFragTopRated.layoutManager = LinearLayoutManager(contextNonNull, RecyclerView.HORIZONTAL, false)
                rvHomeFragTopRated.adapter = adapterTopRated

                rvHomeFragPopular.layoutManager = LinearLayoutManager(contextNonNull, RecyclerView.HORIZONTAL, false)
                rvHomeFragPopular.adapter = adapterPopular

                rvHomeFragTrending.layoutManager = LinearLayoutManager(contextNonNull, RecyclerView.HORIZONTAL, false)
                rvHomeFragTrending.adapter = adapterUpcoming
            }
        }

        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            viewModel = ViewModelProvider(it)[HomeViewModel::class.java]

            viewModel.command = MutableLiveData()

            viewModel.getNowPlayingMovies()

            lifecycleScope.launch {
                viewModel.getMovies(HomeIntent.NowPlaying).collectLatest { pagingData ->
                    adapterNowPlaying.submitData(pagingData)
                }
            }

            lifecycleScope.launch {
                viewModel.getMovies(HomeIntent.TopRated).collectLatest { pagingData ->
                    adapterTopRated.submitData(pagingData)
                }
            }

            lifecycleScope.launch {
                viewModel.getMovies(HomeIntent.Popular).collectLatest { pagingData ->
                    adapterPopular.submitData(pagingData)
                }
            }

            lifecycleScope.launch {
                viewModel.getMovies(HomeIntent.Trending).collectLatest { pagingData ->
                    adapterUpcoming.submitData(pagingData)
                }
            }

            adapterNowPlaying.addLoadStateListener {
                binding?.run {
                    if (it.source.refresh is LoadState.NotLoading) {
                        backgroundDialog.visibility = View.GONE
                        loadingCircularProgressBar.visibility = View.GONE
                    }
                }
            }

            adapterPopular.addLoadStateListener {
                binding?.run {
                    if (it.source.refresh is LoadState.NotLoading) {
                        backgroundDialog.visibility = View.GONE
                        loadingCircularProgressBar.visibility = View.GONE
                    }
                }
            }

            adapterTopRated.addLoadStateListener {
                binding?.run {
                    if (it.source.refresh is LoadState.NotLoading) {
                        backgroundDialog.visibility = View.GONE
                        loadingCircularProgressBar.visibility = View.GONE
                    }
                }
            }

            adapterUpcoming.addLoadStateListener {
                binding?.run {
                    if (it.source.refresh is LoadState.NotLoading) {
                        backgroundDialog.visibility = View.GONE
                        loadingCircularProgressBar.visibility = View.GONE
                    }
                }
            }

            setupObservables()
        }

    }

    private fun setupObservables() {

        viewModel.onSuccessNowPlaying.observe(viewLifecycleOwner) { listNowPlaying ->

            binding?.run {
                carouselView.setViewListener { position ->
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

                carouselView.pageCount = listNowPlaying.size
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}