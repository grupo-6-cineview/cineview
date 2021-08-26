package com.github.grupo6cineview.cineview.features.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.grupo6cineview.cineview.databinding.FragmentLoginTelaBinding
import com.github.grupo6cineview.cineview.features.MainActivity

class LoginTelaFragment : Fragment() {

    private var binding: FragmentLoginTelaBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginTelaBinding.inflate(inflater, container, false)

        binding?.botaoEntrar?.setOnClickListener {
            activity?.let { actnonNull ->
                startActivity(Intent(actnonNull, MainActivity::class.java))
                actnonNull.finish()
            }
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}