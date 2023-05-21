package com.example.armmanager.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.armmanager.AppExecutors
import com.example.armmanager.R
import com.example.armmanager.databinding.AddRequestBinding
import com.example.armmanager.di.Injectable
import com.example.armmanager.ui.request.RequestAdapter
import com.example.armmanager.ui.request.RequestViewModel
import com.example.armmanager.vo.Request
import com.example.armmanager.vo.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddRequestFragment: Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private val viewModel: AddRequestViewModel by viewModels(){ viewModelFactory }

    private lateinit var binding: AddRequestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddRequestBinding.inflate(layoutInflater)

        // access the items of the list
        val statuses = resources.getStringArray(R.array.statuses_array)

        // access the spinner
        val spinner = binding.spinner
        if (spinner != null) {
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, statuses)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                   val status = statuses[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }


            binding.savebtn.setOnClickListener {
            val requestId = 0
            val requestNumber = binding.numberRequestET.text.toString().toInt()
            val requestName = binding.nameRequestET.text.toString()
            val requestDate = binding.creationDateET.text.toString()
            val customer = binding.customerET.text.toString()
            val planDate = binding.planDateET.text.toString()
            val status = statuses[spinner.selectedItemPosition]
            val factDate = binding.factDateET.text.toString()
            val user = 1
            if (requestName.isNotEmpty()) {
                var request = Request(requestId, requestNumber, requestName, customer, planDate, requestDate, factDate, user, status)
                val coroutineScope = CoroutineScope(Dispatchers.Main)
                coroutineScope.launch {
                    viewModel.insertRequest(request)
                }
            }
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val manager = LinearLayoutManager(context) // LayoutManager
//        adapter = RequestAdapter() // Создание объекта
//        binding.currentRequestRV.layoutManager = manager // Назначение LayoutManager для RecyclerView
//        binding.currentRequestRV.adapter = adapter // Назначение адаптера для RecyclerView
//        //addRequestViewModel.log()
//        requestViewModel.log1()
//        //requestViewModel.log2()
//        requestViewModel.requests.observe(viewLifecycleOwner, Observer { requestsResponse ->
//            if (requestsResponse.status == Status.SUCCESS && requestsResponse.data != null)
//                adapter.setData(requestsResponse.data)
//        })
//        /* viewModel.allRequests.observe(viewLifecycleOwner) { requests ->
//             adapter.setData(requests.toMutableList())
//         }*/
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }
}