package com.example.armmanager.ui.auth

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.armmanager.AppExecutors
import com.example.armmanager.R
import com.example.armmanager.databinding.AuthorizationBinding
import com.example.armmanager.di.Injectable
import javax.inject.Inject

class AuthorizationFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appExecutors: AppExecutors

    private val authorizationViewModel: AuthorizationViewModel by viewModels { viewModelFactory }

    private lateinit var binding: AuthorizationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = AuthorizationBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.emailET.text.toString()
        //binding.passwordET.text.toString()

        binding.enter.setOnClickListener { authorizationViewModel.
        login(binding.emailET.text.toString(), binding.passwordET.text.toString() ) }

        binding.registration.setOnClickListener{
            findNavController().navigate(R.id.action_authorizationFragment_to_registerFragment)
        }

    }

}