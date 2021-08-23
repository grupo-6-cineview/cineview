package com.github.grupo6cineview.cineview.features.favorite.apresentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.FragmentFavoriteBinding
import com.github.grupo6cineview.cineview.features.favorite.apresentation.adapter.FavoriteAdapter
import com.github.grupo6cineview.cineview.features.favorite.data.model.recyclerview.FavoriteModel

class FavoriteFragment : Fragment() {

    private var binding: FragmentFavoriteBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        listOf(
            FavoriteModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat",
                "O lutador de MMA Cole Young não conhece sua herança, nem sabe o motivo do Imperador da Exoterra ter enviado seu melhor guerreiro, Sub-Zero...",
                "5.0"
            ),
            FavoriteModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat",
                "O lutador de MMA Cole Young não conhece sua herança, nem sabe o motivo do Imperador da Exoterra ter enviado seu melhor guerreiro, Sub-Zero...",
                "5.0"
            ),
            FavoriteModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat",
                "O lutador de MMA Cole Young não conhece sua herança, nem sabe o motivo do Imperador da Exoterra ter enviado seu melhor guerreiro, Sub-Zero...",
                "5.0"
            ),
            FavoriteModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat",
                "O lutador de MMA Cole Young não conhece sua herança, nem sabe o motivo do Imperador da Exoterra ter enviado seu melhor guerreiro, Sub-Zero...",
                "5.0"
            ),
            FavoriteModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat",
                "O lutador de MMA Cole Young não conhece sua herança, nem sabe o motivo do Imperador da Exoterra ter enviado seu melhor guerreiro, Sub-Zero...",
                "5.0"
            ),
            FavoriteModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat",
                "O lutador de MMA Cole Young não conhece sua herança, nem sabe o motivo do Imperador da Exoterra ter enviado seu melhor guerreiro, Sub-Zero...",
                "5.0"
            ),
            FavoriteModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat",
                "O lutador de MMA Cole Young não conhece sua herança, nem sabe o motivo do Imperador da Exoterra ter enviado seu melhor guerreiro, Sub-Zero...",
                "5.0"
            ),
            FavoriteModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat",
                "O lutador de MMA Cole Young não conhece sua herança, nem sabe o motivo do Imperador da Exoterra ter enviado seu melhor guerreiro, Sub-Zero...",
                "5.0"
            ),
            FavoriteModel(
                R.drawable.mortal_kombat_poster,
                "Mortal Kombat",
                "O lutador de MMA Cole Young não conhece sua herança, nem sabe o motivo do Imperador da Exoterra ter enviado seu melhor guerreiro, Sub-Zero...",
                "5.0"
            )
        ).let { favList ->
            val adapter = FavoriteAdapter(favList)

            binding?.rvFavFragRecycler?.layoutManager = LinearLayoutManager(requireContext())
            binding?.rvFavFragRecycler?.adapter = adapter
        }

        return binding?.root
    }



    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}