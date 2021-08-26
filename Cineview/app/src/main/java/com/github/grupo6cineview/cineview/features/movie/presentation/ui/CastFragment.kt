package com.github.grupo6cineview.cineview.features.movie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.FragmentCastBinding
import com.github.grupo6cineview.cineview.features.movie.presentation.adapter.CastAdapter
import com.github.grupo6cineview.cineview.features.movie.data.model.recyclerview.CastModel

class CastFragment : Fragment() {

    private var binding: FragmentCastBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCastBinding.inflate(inflater, container, false)

        binding?.run {
            listOf(
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)"),
                CastModel(R.drawable.actor_example, "Lewis Tan", "(Cole Young)")
            ).let { castList ->
                val adapter = CastAdapter(castList)

                rvCastFragItems.layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
                rvCastFragItems.adapter = adapter
            }

            rvCastFragItems
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}