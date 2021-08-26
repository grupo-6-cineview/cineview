package com.github.grupo6cineview.cineview.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.github.grupo6cineview.cineview.databinding.ActivitySplashBinding
import com.github.grupo6cineview.cineview.extension.doInTheEnd
import com.github.grupo6cineview.cineview.features.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.laSplashActAnimation.doInTheEnd {
            binding.laSplashActAnimation.visibility = View.GONE
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}