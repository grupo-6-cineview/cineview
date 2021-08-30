package com.github.grupo6cineview.cineview.features.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.databinding.FragmentHomeBinding
import com.github.grupo6cineview.cineview.datamodel.Result
import com.github.grupo6cineview.cineview.extensions.BaseFragment
import com.github.grupo6cineview.cineview.extensions.Command
import com.github.grupo6cineview.cineview.features.home.viewmodel.HomeViewModel

class HomeFragment : BaseFragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel


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



        activity?.let {
            viewModel = ViewModelProvider(it)[HomeViewModel::class.java]

            viewModel.command = command

            viewModel.getNowPlayingMovies()


            setupObservables()
        }

    }
    private fun setupObservables() {
        viewModel.onSuccessNowPlaying.observe(viewLifecycleOwner, {
            it?.let { nowPlayingList ->
                val imgs = mutableListOf<Int>()
                nowPlayingList.forEach {
                    imgs.add(
                        it.backdrop_path.toInt()
                    )
                }


                binding?.carouselView?.pageCount = imgs.size
                binding?.carouselView?.setImageListener { position, imageView ->

                    imageView.setImageResource(imgs[position])


                    binding?.carouselView?.setImageClickListener{position ->
                        println("Clicked $position")
                    }
                }


            }

        })

        viewModel.onErrorNowPlaying.observe(viewLifecycleOwner, {
            it
        })

        viewModel.onCustomErrorNowPlaying.observe(viewLifecycleOwner, {
            //abrir uma activity
            //abrir um viewGroup
            //mensagem via SnackBar
        })



    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
    override var command: MutableLiveData<Command> = MutableLiveData()
}