package com.github.grupo6cineview.cineview.features.search.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.FragmentSearchBinding
import com.github.grupo6cineview.cineview.extension.asDp
import com.github.grupo6cineview.cineview.extension.getDrawable2
import com.github.grupo6cineview.cineview.features.search.adapter.SearchAdapter
import com.github.grupo6cineview.cineview.features.search.presentation.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null
    private lateinit var viewModel: SearchViewModel

    private val searchAdapter by lazy {
        SearchAdapter {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

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
            }

            rvSearchFragRecycler.layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
            rvSearchFragRecycler.adapter = searchAdapter

        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            tietSearchFragSearchField.doOnTextChanged { _, _, before, count ->
                viewModel.getSearchResult(tietSearchFragSearchField.text?.toString() ?: "")
            }
        }

        setupObservables()
    }

    private fun setupObservables() {
        viewModel.onSuccessSearch.observe(viewLifecycleOwner) { listResult ->
            searchAdapter.submitList(listResult)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}