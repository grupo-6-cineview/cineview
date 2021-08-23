package com.github.grupo6cineview.cineview.features.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.grupo6cineview.cineview.databinding.FragmentLoginTelaBinding
import com.github.grupo6cineview.cineview.features.movie.apresentation.ui.MovieFragment


class LoginTelaFragment : Fragment() {

    private var binding: FragmentLoginTelaBinding? = null
    private val movie = MovieFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginTelaBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Teste feitos pelo Pedro - podem ser excluídas as linhas 31 a 35
        binding?.run {
            botaoEntrar.setOnClickListener {
                movie.show(parentFragmentManager, "BOTTOM_SHEET")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}