package com.github.grupo6cineview.cineview.features.movie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.grupo6cineview.cineview.databinding.FragmentDetailsBinding
import com.github.grupo6cineview.cineview.features.movie.presentation.adapter.DetailsAdapter
import com.github.grupo6cineview.cineview.features.movie.data.model.recyclerview.DetailsModel

class DetailsFragment : Fragment() {
    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        listOf(
            DetailsModel("Genêros", "Ação, Fantasia, Aventura"),
            DetailsModel("Diretores", "Simon McQuoid"),
            DetailsModel("Idioma Original", "English"),
            DetailsModel("Orçamento", "R$20.000.000,00"),
            DetailsModel("Receita", "R$83.601.013,00"),
            DetailsModel("Estúdios", "Atomic Monster, Broken Road Productions, New Line Cinema")
        ).let { listDetails ->
            val adapter = DetailsAdapter(listDetails)

            binding?.run {
                rvDetailsFragItems.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                rvDetailsFragItems.adapter = adapter
            }
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}