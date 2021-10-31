package com.github.grupo6cineview.cineview.features.search.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.FragmentSearchBinding
import com.github.grupo6cineview.cineview.extension.*
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Detail.BUNDLE_KEY_MOVIE_ID
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Detail.TAG_SHOW_DETAIL_FRAGMENT
import com.github.grupo6cineview.cineview.features.movie.presentation.ui.MovieFragment
import com.github.grupo6cineview.cineview.features.search.presentation.adapter.SearchAdapter
import com.github.grupo6cineview.cineview.features.search.presentation.viewmodel.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null
    private val viewModel: SearchViewModel by viewModel()
    private val movieFragment: MovieFragment get() = MovieFragment { id -> onClickMovie(id) }
    private var job: Job? = null
    private var lastSearch: String? = null

    private val searchAdapter by lazy {
        SearchAdapter { id -> onClickMovie(id) }
    }

    private fun onClickMovie(id: Int) {
        with(movieFragment) {
            Bundle().run {
                putInt(BUNDLE_KEY_MOVIE_ID, id)

                arguments = this
            }

            show(this@SearchFragment.parentFragmentManager, TAG_SHOW_DETAIL_FRAGMENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setupInputLayout()
        setupRecycler()
        setupSearchAnimation()

        return binding?.root
    }

    private fun setupInputLayout() =
        binding?.run {
            ilSearchFragSearchField.editText?.setOnFocusChangeListener { _, hasFocus ->
                with(ilSearchFragSearchField) {
                    if (hasFocus) {
                        (layoutParams as ViewGroup.MarginLayoutParams).let { params ->
                            params.setMargins(8.asDp(), 0.asDp(), 8.asDp(), 0.asDp())
                            layoutParams = params
                        }

                        ilSearchFragSearchField.startIconDrawable = context?.getDrawable2(R.drawable.ic_baseline_back_edit_text)
                    } else {
                        (layoutParams as ViewGroup.MarginLayoutParams).let { params ->
                            params.setMargins(8.asDp(), 8.asDp(), 8.asDp(), 8.asDp())
                            layoutParams = params
                        }

                        ilSearchFragSearchField.startIconDrawable = context?.getDrawable2(R.drawable.ic_baseline_search)
                    }
                }
            }

            ilSearchFragSearchField.setStartIconOnClickListener {
                ilSearchFragSearchField.clearFocus()
                context?.hideKeyboard(ilSearchFragSearchField)
            }
        }

    private fun setupRecycler() =
        binding?.run {
            rvSearchFragRecycler.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            rvSearchFragRecycler.adapter = searchAdapter
        }

    private fun setupSearchAnimation() {
        binding?.run {
            searchAnimation.setMaxFrame(60)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.command = MutableLiveData()
        getMoviesBySearch()
        setupState()
        setupListeners()
    }

    private fun getMoviesBySearch(retry: Boolean = false) =
        binding?.run {
            if (retry) {
                setupLayoutBySearch(lastSearch == "")
                callMoviesBySearch(lastSearch ?: "")
            } else {
                tietSearchFragSearchField.doAfterTextChanged { search ->
                    lastSearch = search.toString()

                    setupLayoutBySearch(lastSearch == "")
                    callMoviesBySearch(lastSearch ?: "")
                }
            }
        }

    private fun setupLayoutBySearch(emptySearch: Boolean) {
        binding?.run {
            if (searchAnimation.isVisible)
                searchAnimation.visibility = GONE

            if (emptySearch) {
                emptyAnimation.visibility = VISIBLE
                emptyAnimation.playAnimation()
                rvSearchFragRecycler.visibility = GONE
            } else {
                emptyAnimation.visibility = GONE
                rvSearchFragRecycler.visibility = VISIBLE
            }
        }
    }

    private fun callMoviesBySearch(search: String) {
        job?.cancel()

        job = lifecycleScope.launch {
            viewModel.getMovieBySearch(search).collectLatest { pagingData ->
                searchAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupState() {
        binding?.run {
            lifecycleScope.launch {
                searchAdapter.loadStateFlow.collect { loadState ->
                    loadingLayout.root.visibility = if (loadState.isLoading()) VISIBLE else GONE
                    errorLayout.root.visibility = if (loadState.isError()) VISIBLE else GONE

                    lastSearch?.let {
                        if (loadState.isNotLoading() && searchAdapter.itemCount == 0)
                            setupLayoutBySearch(true)
                    }
                }
            }
        }
    }

    private fun setupListeners() {
        binding?.run {
            errorLayout.btRefresh.setOnClickListener {
                getMoviesBySearch(retry = true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}