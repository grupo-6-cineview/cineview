package com.github.grupo6cineview.cineview.features.account_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.ActivityAccountScreenBinding
import com.github.grupo6cineview.cineview.features.account_screen.view.AccountScreenFragment
import com.github.grupo6cineview.cineview.features.account_screen.view.AccountScreenFragment.Companion.newInstance

class AccountScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AccountScreenFragment.newInstance())
                .commitNow()
        }
    }
}