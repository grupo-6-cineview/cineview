package com.github.grupo6cineview.cineview.features.home.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.base.BaseFragment
import com.github.grupo6cineview.cineview.databinding.FragmentHomeBinding
import com.github.grupo6cineview.cineview.extensions.Command
import com.github.grupo6cineview.cineview.extensions.ConstantsApp
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Api.PATH_TRENDING_DAY
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Api.PATH_TRENDING_MOVIE
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Api.PATH_TRENDING_TV
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Api.PATH_TRENDING_WEEK
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Home.BUNDLE_KEY_ID
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Home.BUNDLE_KEY_MEDIA_TYPE
import com.github.grupo6cineview.cineview.features.home.presentation.adapter.HomeAdapter
import com.github.grupo6cineview.cineview.features.home.presentation.viewmodel.HomeViewModel
import com.github.grupo6cineview.cineview.features.movie.movie.presentation.ui.MovieFragment

class HomeFragment : BaseFragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel
    private val movieFragment: MovieFragment get() = MovieFragment()

    private val adapterTMDay by lazy {
        HomeAdapter { id, mediaType ->
            with(movieFragment) {
                Bundle().run {
                    putInt(BUNDLE_KEY_ID, id)
                    putString(BUNDLE_KEY_MEDIA_TYPE, mediaType)

                    arguments = this
                }

                show(this@HomeFragment.parentFragmentManager, "BOTTOM_SHEET_FRAG")
            }
        }
    }

    private val adapterTMWeek by lazy {
        HomeAdapter { id, mediaType ->
            with(movieFragment) {
                Bundle().run {
                    putInt(BUNDLE_KEY_ID, id)
                    putString(BUNDLE_KEY_MEDIA_TYPE, mediaType)

                    arguments = this
                }

                show(this@HomeFragment.parentFragmentManager, "BOTTOM_SHEET_FRAG")
            }
        }
    }

    private val adapterTTDay by lazy {
        HomeAdapter { id, mediaType ->
            with(movieFragment) {
                Bundle().run {
                    putInt(BUNDLE_KEY_ID, id)
                    putString(BUNDLE_KEY_MEDIA_TYPE, mediaType)

                    arguments = this
                }

                show(this@HomeFragment.parentFragmentManager, "BOTTOM_SHEET_FRAG")
            }
        }
    }

    private val adapterTTWeek by lazy {
        HomeAdapter { id, mediaType ->
            with(movieFragment) {
                Bundle().run {
                    putInt(BUNDLE_KEY_ID, id)
                    putString(BUNDLE_KEY_MEDIA_TYPE, mediaType)

                    arguments = this
                }

                show(this@HomeFragment.parentFragmentManager, "BOTTOM_SHEET_FRAG")
            }
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
                rvHomeFragTMDay.layoutManager = LinearLayoutManager(contextNonNull, RecyclerView.HORIZONTAL, false)
                rvHomeFragTMDay.adapter = adapterTMDay

                rvHomeFragTMWeek.layoutManager = LinearLayoutManager(contextNonNull, RecyclerView.HORIZONTAL, false)
                rvHomeFragTMWeek.adapter = adapterTMWeek

                rvHomeFragTTDay.layoutManager = LinearLayoutManager(contextNonNull, RecyclerView.HORIZONTAL, false)
                rvHomeFragTTDay.adapter = adapterTTDay

                rvHomeFragTTWeek.layoutManager = LinearLayoutManager(contextNonNull, RecyclerView.HORIZONTAL, false)
                rvHomeFragTTWeek.adapter = adapterTTWeek
            }

        }

        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            viewModel = ViewModelProvider(it)[HomeViewModel::class.java]

            viewModel.command = command

            viewModel.getNowPlayingMovies()

            ConstantsApp.Home.run {
                viewModel.getTrendingMovies(PATH_TRENDING_MOVIE, PATH_TRENDING_DAY)
                viewModel.getTrendingMovies(PATH_TRENDING_MOVIE, PATH_TRENDING_WEEK)
                viewModel.getTrendingMovies(PATH_TRENDING_TV, PATH_TRENDING_DAY)
                viewModel.getTrendingMovies(PATH_TRENDING_TV, PATH_TRENDING_WEEK)
            }

            setupObservables()
        }

    }

    private fun setupObservables() {
        viewModel.customCommand.observe(viewLifecycleOwner) { isLoading ->
            binding?.run {
                if (isLoading) {
                    backgroundDialog.visibility = View.VISIBLE
                    loadingCircularProgressBar.visibility = View.VISIBLE
                } else {
                    backgroundDialog.visibility = View.GONE
                    loadingCircularProgressBar.visibility = View.GONE
                }
            }
        }

        viewModel.onSuccessNowPlaying.observe(viewLifecycleOwner, {
            it?.let { nowPlayingList ->

                val imgs = mutableListOf<String>()

                for (i in 0..4) {
                    nowPlayingList[i].backdrop_path?.let { backdrop ->
                        imgs.add(
                            backdrop
                        )
                    }
                }

                binding?.carouselView?.setImageListener { position, imageView ->

                    context?.let { contextNonNull ->
                        Glide.with(contextNonNull)
                            .load(imgs[position])
                            .into(imageView)
                    }

                    binding?.carouselView?.setImageClickListener { p ->
                        println("Clicked $p")
                    }

                }
                binding?.carouselView?.pageCount = imgs.size


            }

        })

        viewModel.onSuccessTMDay.observe(viewLifecycleOwner) { resultList ->
            adapterTMDay.submitList(resultList)
        }

        viewModel.onSuccessTMWeek.observe(viewLifecycleOwner) { resultList ->
            adapterTMWeek.submitList(resultList)
        }

        viewModel.onSuccessTTDay.observe(viewLifecycleOwner) { resultList ->
            adapterTTDay.submitList(resultList)
        }

        viewModel.onSuccessTTWeek.observe(viewLifecycleOwner) { resultList ->
            adapterTTWeek.submitList(resultList)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    override var command: MutableLiveData<Command> = MutableLiveData()

}