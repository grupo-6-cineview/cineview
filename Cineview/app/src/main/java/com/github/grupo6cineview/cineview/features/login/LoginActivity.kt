package com.github.grupo6cineview.cineview.features.login

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.features.movie.MovieFragment

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.buttonTest).setOnClickListener {
            MovieFragment().show(supportFragmentManager, "TAG")
        }
    }
}