package com.github.grupo6cineview.cineview.features.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}