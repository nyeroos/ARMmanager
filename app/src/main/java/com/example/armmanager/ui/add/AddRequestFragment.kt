package com.example.armmanager.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.armmanager.R
import com.example.armmanager.databinding.AddRequestBinding

class AddRequestFragment: Fragment() {
    private lateinit var binding: AddRequestBinding

    private val viewModel: AddRequestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddRequestBinding.inflate(layoutInflater)
        return binding.root
    }
}