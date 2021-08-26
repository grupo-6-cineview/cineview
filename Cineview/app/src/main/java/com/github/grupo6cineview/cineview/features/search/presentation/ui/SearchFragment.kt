package com.github.grupo6cineview.cineview.features.search.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.FragmentSearchBinding
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
            val adapter = MovieAdapter(movieList)

            binding?.run {
                rvSearchFragRecycler.layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
                rvSearchFragRecycler.adapter = adapter
            }
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}