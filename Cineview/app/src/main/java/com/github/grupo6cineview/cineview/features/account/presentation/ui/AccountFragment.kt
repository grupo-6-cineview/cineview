package com.github.grupo6cineview.cineview.features.account.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.facebook.login.LoginManager
import com.github.grupo6cineview.cineview.R
import com.github.grupo6cineview.cineview.databinding.FragmentAccountBinding
import com.github.grupo6cineview.cineview.features.account.presentation.viewmodel.AccountViewModel
import com.github.grupo6cineview.cineview.features.login.presentation.ui.LoginActivity
import com.github.grupo6cineview.cineview.utils.ShareHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment() {

    private var binding: FragmentAccountBinding? = null
    private val viewModel by viewModel<AccountViewModel>()
    private lateinit var auth: FirebaseAuth
    private val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        GoogleSignIn.getClient(requireActivity(), gso)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth.currentUser?.let { user ->
            setView(user)
            setListener()
        }
    }

    private fun setView(user: FirebaseUser) = binding?.run {
        user.photoUrl?.let {
            Glide.with(requireContext())
                .load(it)
                .centerCrop()
                .into(accountProfilePhoto)
        }

        accountProfileName.text =
            if (user.displayName == "" || user.displayName == null)
                getString(R.string.user_anonymous)
            else
                user.displayName
        accountProfileEmailValue.text =
            if (user.email == "" || user.email == null)
                getString(R.string.account_no_data)
            else
                user.email
        accountProfilePhoneValue.text =
            if (user.phoneNumber == "" || user.phoneNumber == null)
                getString(R.string.account_no_data)
            else
                user.phoneNumber
        context?.let { accountDeviceShareCountValue.text = ShareHelper.getShareCount(it).toString() }

        lifecycleScope.launch {
            accountDeviceFavoriteValue.text = viewModel.getFavoritesSize().single().toString()
        }
    }

    private fun setListener() = binding?.run {
        accountButtonLogout.setOnClickListener {
            auth.signOut()
            googleSignInClient.signOut()
            LoginManager.getInstance().logOut()
            activity?.let { act ->
                startActivity(
                    Intent(act, LoginActivity::class.java)
                )
                act.finish()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}