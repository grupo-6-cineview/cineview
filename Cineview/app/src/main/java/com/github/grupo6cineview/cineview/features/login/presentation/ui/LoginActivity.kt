package com.github.grupo6cineview.cineview.features.login.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.ActivityLoginBinding
import com.github.grupo6cineview.cineview.extension.setVisible
import com.github.grupo6cineview.cineview.features.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    private val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        GoogleSignIn.getClient(this, gso)
    }

    private val loginGoogleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            loginWithGoogle(
                task.getResult(ApiException::class.java).idToken
            )
        } catch (e: ApiException) {
            Toast.makeText(
                this,
                getString(
                    R.string.error_login,
                    "Google"
                ),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private val callbackManager by lazy {
        CallbackManager.Factory.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        setupFacebookButton()
    }

    private fun setupFacebookButton() = binding.run {
        buttonLoginFacebook.setReadPermissions("email", "public_profile")
        buttonLoginFacebook.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                loginWithFacebook(
                    loginResult.accessToken
                )
            }

            override fun onCancel() {}

            override fun onError(error: FacebookException) {
                Toast.makeText(
                    this@LoginActivity,
                    getString(
                        R.string.error_login,
                        "Facebook"
                    ),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    public override fun onStart() {
        super.onStart()
        checkLogin(
            logged = auth.currentUser
        )
    }

    override fun onResume() {
        super.onResume()
        binding.run {
            buttonLoginAnonymous.setOnClickListener {
                checkLogin(
                    logged = auth.currentUser,
                    anonymous = true
                )
            }

            buttonLoginGoogle.setOnClickListener {
                checkLogin(
                    logged = auth.currentUser,
                    google = true
                )
            }

            customButtonLoginFacebook.setOnClickListener {
                buttonLoginFacebook.callOnClick()
            }
        }
    }

    private fun checkLogin(
        logged: FirebaseUser?,
        anonymous: Boolean = false,
        google: Boolean = false,
        facebook: Boolean = false
    ) {
        if (logged != null) {
            setLoading(visible = true)
            startActivity(
                Intent(this, MainActivity::class.java)
            )
            finish()
        } else {
            when {
                anonymous -> loginAnonymously()
                google -> loginGoogleLauncher.launch(googleSignInClient.signInIntent)
                facebook -> binding.buttonLoginFacebook.callOnClick()
            }
        }
    }

    private fun loginAnonymously() {
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    checkLogin(
                        logged = auth.currentUser,
                        anonymous = true
                    )
                } else {
                    Toast.makeText(
                        this,
                        getString(
                            R.string.error_login,
                            "Modo Anônimo"
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun loginWithGoogle(idToken: String?) {
        auth.signInWithCredential(
            GoogleAuthProvider.getCredential(idToken, null)
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    checkLogin(
                        logged = auth.currentUser,
                        google = true
                    )
                } else {
                    Toast.makeText(
                        this,
                        getString(
                            R.string.error_login,
                            "Google"
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun loginWithFacebook(token: AccessToken) {
        auth.signInWithCredential(
            FacebookAuthProvider.getCredential(token.token)
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    checkLogin(
                        logged = auth.currentUser,
                        facebook = true
                    )
                } else {
                    Toast.makeText(
                        this,
                        getString(
                            R.string.error_login,
                            "Facebook"
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        setLoading(visible = false)
    }

    private fun setLoading(visible: Boolean) = binding.run {
        loadingLayout.root.setVisible(visible = visible)
        buttonLoginAnonymous.setVisible(visible = !visible, useInvisible = true)
        buttonLoginGoogle.setVisible(visible = !visible, useInvisible = true)
        buttonLoginFacebook.setVisible(visible = !visible, useInvisible = true)
        customButtonLoginFacebook.setVisible(visible = !visible, useInvisible = true)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}