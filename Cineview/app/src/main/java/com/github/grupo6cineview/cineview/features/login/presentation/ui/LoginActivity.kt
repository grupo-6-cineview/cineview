package com.github.grupo6cineview.cineview.features.login.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.grupo6cineview.cineview.databinding.ActivityLoginBinding
import com.github.grupo6cineview.cineview.features.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.buttonLoginAnonymous.setOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
            finish()
        }
    }
}