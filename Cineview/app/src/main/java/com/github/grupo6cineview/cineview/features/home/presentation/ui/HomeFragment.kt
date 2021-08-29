package com.github.grupo6cineview.cineview.features.home.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val imgs = intArrayOf(
        R.drawable.avengers,
        R.drawable.aquaman,
        R.drawable.mortal,
        R.drawable.harry,
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    binding?.let {
        it.carouselView.pageCount =imgs.size
        it.carouselView.setImageListener{position,imageView ->
            imageView.setImageResource(imgs[position])
        }
        it.carouselView.setImageClickListener{position ->
            println("Clicked $position")
        }

    }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}