package com.github.grupo6cineview.cineview.features.account_screen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.ActivityAccountScreenBinding


class AccountScreenFragment : Fragment() {
    companion object {
        fun newInstance() = AccountScreenFragment()
    }

        private var binding : ActivityAccountScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = ActivityAccountScreenBinding.inflate(inflater,container,false)
        return binding?.root
    }


}