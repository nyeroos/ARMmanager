package com.example.armmanager.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.armmanager.databinding.AddRequestBinding
import com.example.armmanager.ui.request.RequestViewModel

class AddRequestFragment: Fragment() {
    private lateinit var binding: AddRequestBinding

    private val viewModel: RequestViewModel by viewModels()

    private lateinit var editWordView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddRequestBinding.inflate(layoutInflater)

        //editWordView = binding.editTextDate
        //val button = binding.button6

        binding.button6.setOnClickListener {
            val requestId = 0
            val requestNumber = binding.numberRequestET.text.toString().toInt()
            val requestName = binding.nameRequestET.text.toString()
            val requestDate = binding.creationDateET.text.toString()
            val customer = binding.customerET.text.toString()
            val planDate = binding.planDateET.text.toString()
            val status = binding.statusCT.text.toString()
            val factDate = binding.factDateET.text.toString()
            val user = 1
            if (requestName.isNotEmpty()) {
                //viewModel.insert(Request(requestId, requestNumber, requestName, customer, planDate, requestDate, factDate, user, status))
            }
            findNavController().navigateUp()
        }

        return binding.root
    }
}