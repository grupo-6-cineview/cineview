package com.github.grupo6cineview.cineview.features.search.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.FragmentSearchBinding
import com.github.grupo6cineview.cineview.extension.asDp
import com.github.grupo6cineview.cineview.extension.getDrawable2
import com.github.grupo6cineview.cineview.extension.hideKeyboard
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Detail.BUNDLE_KEY_ID
import com.github.grupo6cineview.cineview.extensions.ConstantsApp.Detail.TAG_SHOW_DETAIL_FRAGMENT
import com.github.grupo6cineview.cineview.features.movie.movie.presentation.ui.MovieFragment
import com.github.grupo6cineview.cineview.features.search.presentation.adapter.SearchAdapter
import com.github.grupo6cineview.cineview.features.search.presentation.viewmodel.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null
    private lateinit var viewModel: SearchViewModel
    private val movieFragment: MovieFragment get() = MovieFragment()
    private var job: Job? = null

    private val searchAdapter by lazy {
        SearchAdapter { id -> onClickMovie(id) }
    }

    private fun onClickMovie(id: Int) {
        with(movieFragment) {
            Bundle().run {
                putInt(BUNDLE_KEY_ID, id)

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

        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        setupInputLayout()
        setupRecycler()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMoviesBySearch()
    }

    private fun getMoviesBySearch() =
        binding?.run {
            tietSearchFragSearchField.doAfterTextChanged { search ->
                callMoviesBySearch(search.toString())
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

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}