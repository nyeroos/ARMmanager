package com.example.armmanager.ui.register

import androidx.fragment.app.Fragment
import com.example.armmanager.di.Injectable
import javax.inject.Inject
import androidx.lifecycle.ViewModelProvider
import com.example.armmanager.AppExecutors
import androidx.fragment.app.viewModels
import com.example.armmanager.databinding.RegistrationBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.armmanager.R

class RegisterFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private val registerViewModel: RegisterViewModel by viewModels { viewModelFactory }

    private lateinit var binding: RegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = RegistrationBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.done.setOnClickListener {
            if (binding.passwordET.text.toString().equals(binding.confirmPassET.text.toString())) {
                registerViewModel.register(
                    binding.userNameET.text.toString(),
                    binding.emailET.text.toString(),
                    binding.passwordET.text.toString()
                )
                findNavController().navigate(R.id.action_registerFragment_to_authorizationFragment)
            }
        }
    }
}