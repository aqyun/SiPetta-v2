package com.dicoding.sipetta.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dicoding.sipetta.R
import com.dicoding.sipetta.ViewModelFactory
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dicoding.sipetta.data.api.ApiConfig
import com.dicoding.sipetta.data.pref.UserPreference
import com.dicoding.sipetta.data.pref.dataStore
import com.dicoding.sipetta.view.welcome.WelcomeActivity
import de.hdodenhof.circleimageview.CircleImageView

@Suppress("DEPRECATION")
class ProfileFragment : Fragment() {

    private lateinit var avatarImageView: CircleImageView
    private lateinit var tvUsername: TextView
    private lateinit var logoutButton: Button

    private val apiService = ApiConfig.getApiService()

    private lateinit var userPreference: UserPreference

    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireContext().applicationContext, apiService)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        avatarImageView = view.findViewById(R.id.avatarImageView)
        tvUsername = view.findViewById(R.id.tv_username)
        logoutButton = view.findViewById(R.id.logoutButton)

        avatarImageView.setImageResource(R.drawable.avatar)

        logoutButton.setOnClickListener {
            startActivity(Intent(context, WelcomeActivity::class.java))
            requireActivity().finish()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userPreference = UserPreference.getInstance(requireContext().dataStore)

        lifecycleScope.launchWhenStarted {
            userPreference.getSession().collect { userModel ->
                val userToken = userModel.token
                viewModel.fetchUserDetail(userToken)
            }
        }

        viewModel.userDetail.observe(viewLifecycleOwner) { user ->
            user?.let {
                val username = it.data?.name ?: "DefaultUsername"
                Log.d("ProfileFragment", "Username: $username")
                tvUsername.text = username
            }
        }
    }
}
