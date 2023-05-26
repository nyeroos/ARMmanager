package com.example.armmanager.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.armmanager.AppExecutors
import com.example.armmanager.R
import com.example.armmanager.databinding.AddRequestBinding
import com.example.armmanager.di.Injectable
import com.example.armmanager.ui.add.AddRequestViewModel
import com.example.armmanager.ui.request.RequestViewModel
import com.example.armmanager.vo.Request
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController
import javax.inject.Inject

class EditRequestFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private val viewModel: AddRequestViewModel by viewModels { viewModelFactory }

    private lateinit var binding: AddRequestBinding
    private lateinit var currentRequest: Request

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = AddRequestBinding.inflate(inflater)

        val statuses = resources.getStringArray(R.array.statuses_array)

        val spinner = binding.spinner
        if (spinner != null) {
            val adapter = ArrayAdapter(
                requireContext(), android.R.layout.simple_spinner_item, statuses
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    val status = statuses[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
        arguments?.let {
            currentRequest = it.getParcelable("value")
                ?: throw IllegalArgumentException("Request object is null")
            currentRequest?.let {
                binding.numberRequestET.setText(currentRequest.number.toString())
                binding.nameRequestET.setText(currentRequest.name)
                binding.customerET.setText(currentRequest.customer)
                binding.creationDateET.setText(currentRequest.creationDate)
                binding.planDateET.setText(currentRequest.expectedDate)
                binding.factDateET.setText(currentRequest.actualDate)
                binding.spinner.setSelection(statuses.indexOf(currentRequest.status))
            }
        }


        binding.savebtn.setOnClickListener {
            val requestId = currentRequest.id
            val requestNumber = binding.numberRequestET.text.toString().toInt()
            val requestName = binding.nameRequestET.text.toString()
            val requestDate = binding.creationDateET.text.toString()
            val customer = binding.customerET.text.toString()
            val planDate = binding.planDateET.text.toString()
            val status = statuses[spinner.selectedItemPosition]
            val factDate = binding.factDateET.text.toString()
            val user = 1
            if (requestName.isNotEmpty()) {
                val updatedRequest = Request(
                    requestId,
                    requestNumber,
                    requestName,
                    customer,
                    planDate,
                    requestDate,
                    factDate,
                    user,
                    status
                )
                val coroutineScope = CoroutineScope(Dispatchers.Main)
                coroutineScope.launch {
                    viewModel.updateRequest(updatedRequest)
                }
            }
            findNavController().navigateUp()
        }

        return binding.root
    }
}