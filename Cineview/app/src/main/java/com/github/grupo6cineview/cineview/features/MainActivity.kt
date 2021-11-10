package com.github.grupo6cineview.cineview.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.bumptech.glide.Glide
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val profile by lazy {
        FirebaseAuth.getInstance().currentUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnMainActFlow.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {  }
                R.id.searchFragment -> {  }
                R.id.favoriteFragment -> {  }
                R.id.accountFragment -> {  }
            }
        }

        profile?.run {
            Glide.with(this@MainActivity)
                .load(photoUrl)
                .centerCrop()
                .into(binding.bnMainProfilePhoto)
        }

        Navigation.findNavController(this, R.id.nav_host_fragment).let { navController ->
            setupWithNavController(binding.bnMainActFlow, navController)
        }
    }
}