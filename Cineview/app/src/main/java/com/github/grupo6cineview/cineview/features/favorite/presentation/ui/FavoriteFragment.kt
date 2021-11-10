package com.github.grupo6cineview.cineview.features.favorite.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.grupo6cineview.cineview.databinding.FragmentFavoriteBinding
import com.github.grupo6cineview.cineview.extension.setVisible
import com.github.grupo6cineview.cineview.features.favorite.presentation.adapter.FavoriteAdapter
import com.github.grupo6cineview.cineview.features.favorite.presentation.viewmodel.FavoriteViewModel
import com.github.grupo6cineview.cineview.features.home.domain.HomeIntent
import com.github.grupo6cineview.cineview.features.movie.presentation.ui.MovieFragment
import com.github.grupo6cineview.cineview.utils.ConstantsApp
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private var binding: FragmentFavoriteBinding? = null
    private val viewModel by viewModel<FavoriteViewModel>()
    private val movieFragment: MovieFragment get() = MovieFragment(
        onClick = { id -> onCLickMovie(id) },
        notifyFavoriteDelete = {
            viewModel.callFavorites()
        }
    )

    private val adapter = FavoriteAdapter(
        onClickFavorite = { movieId ->
            viewModel.deleteFavorite(movieId)
        },
        onClickMovie = { movieId ->
            onCLickMovie(id = movieId)
        },
        scrollAction = {
            binding?.rvFavFragRecycler?.smoothScrollToPosition(0)
        }
    )

    private fun onCLickMovie(
        id: Int,
        intent: HomeIntent? = null
    ) {
        Bundle().let { bundle ->
            bundle.putInt(ConstantsApp.Detail.BUNDLE_KEY_MOVIE_ID, id)
            intent?.let { bundle.putString(ConstantsApp.Detail.BUNDLE_KEY_HOME_INTENT, it.name) }

            movieFragment.apply {
                arguments = bundle
                show(this@FavoriteFragment.parentFragmentManager,
                    ConstantsApp.Detail.TAG_SHOW_DETAIL_FRAGMENT
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListeners()
        viewModel.callFavorites()
        setupObservables()
    }

    private fun setupRecyclerView() = binding?.run {
        rvFavFragRecycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvFavFragRecycler.adapter = adapter
    }

    private fun setupListeners() = binding?.run {
        refreshLayout.setOnRefreshListener {
            viewModel.callFavorites()
        }
    }

    private fun setupObservables() = with(viewModel) {
        onSuccessFavorites.observe(viewLifecycleOwner) { favoriteViewParams ->
            binding?.run {
                if (favoriteViewParams.isEmpty()) {
                    rvFavFragRecycler.setVisible(visible = false, useInvisible = true)
                    favoriteEmptyAnim.setVisible(visible = true)
                    favoriteEmptyAnim.playAnimation()
                } else {
                    if (!rvFavFragRecycler.isVisible) rvFavFragRecycler.setVisible(visible = true, useInvisible = true)
                    favoriteEmptyAnim.setVisible(visible = false)
                }

                adapter.submitList(favoriteViewParams)
                refreshLayout.isRefreshing = false
            }
        }

        onSuccessDelete.observe(viewLifecycleOwner) {
            viewModel.callFavorites()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}