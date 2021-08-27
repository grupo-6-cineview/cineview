package com.github.grupo6cineview.cineview.features.search.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.FragmentSearchBinding
import com.github.grupo6cineview.cineview.extension.asDp
import com.github.grupo6cineview.cineview.extension.getDrawable2
import com.github.grupo6cineview.cineview.features.movie.presentation.ui.MovieFragment
import com.github.grupo6cineview.cineview.features.search.data.model.recyclerview.MovieModel
import com.github.grupo6cineview.cineview.features.search.presentation.adapter.MovieAdapter

class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        listOf(
            MovieModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat"
            ),
            MovieModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat"
            ),
            MovieModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat"
            ),
            MovieModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat"
            ),
            MovieModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat"
            ),
            MovieModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat"
            ),
            MovieModel(
                R.drawable.mortal_kombat_poster,
                "Velozes & Furiosos 9: A Saga Velozes & Furiosos"
            ),
            MovieModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat"
            ),
            MovieModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat"
            ),
            MovieModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat"
            ),
            MovieModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat"
            ),
            MovieModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat"
            )
        ).let { movieList ->
            val adapter = MovieAdapter(movieList) {

                with(MovieFragment) {
                    backdropId = R.drawable.mortal_kombat_backdrop
                    title = "Mortal Kombat"
                    rateCount = "5.0"
                    overView = "O lutador de MMA Cole Young não conhece sua herança, nem sabe o motivo do Imperador da Exoterra ter enviado seu melhor guerreiro, Sub-Zero, para ir atrás dele. Temendo pela segurança de sua família, ele se une a outros heróis para proteger a Terra."
                    releaseDate = "07/04/2021"
                    runtimeDuration = "110min"

                    newInstance.show(parentFragmentManager, "BOTTOM_SHEET")
                }

            }

            binding?.run {
                rvSearchFragRecycler.layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
                rvSearchFragRecycler.adapter = adapter
            }
        }

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

        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}